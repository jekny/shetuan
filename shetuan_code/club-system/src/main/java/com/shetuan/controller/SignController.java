package com.shetuan.controller;

/**
 * ClassName: SignController
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:23
 * @Version 1.0
 */

import com.shetuan.service.ActivitySignService;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/sign")
public class SignController {
    private final ActivitySignService signService;
    public SignController(ActivitySignService signService) {
        this.signService = signService;
    }

    @GetMapping("/create/{activityId}")
    public R<String> createQr(@PathVariable Long activityId, HttpServletRequest request) {
        String role = (String) request.getAttribute("loginRole");
        if (!"LEADER".equals(role) && !"ADMIN".equals(role)) {
            return R.fail("仅负责人可生成签到码");
        }
        String qrCode = activityId + "_" + System.currentTimeMillis() + "_" + UUID.randomUUID();
        return R.ok(qrCode);
    }

    @PostMapping("/scan")
    public R<String> scanSign(@RequestParam String qrCode, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("loginUserId");
        String[] arr = qrCode.split("_");
        Long actId = Long.valueOf(arr[0]);
        return signService.sign(actId, userId);
    }

    @PostMapping("/add")
    public R<String> manualSign(@RequestParam Long activityId, @RequestParam Long studentId, HttpServletRequest request) {
        String role = (String) request.getAttribute("loginRole");
        if (!"LEADER".equals(role) && !"ADMIN".equals(role)) {
            return R.fail("仅负责人可手动补签");
        }
        return signService.manualSign(activityId, studentId);
    }

    @GetMapping("/count/{activityId}")
    public R<?> signCount(@PathVariable Long activityId) {
        return signService.getSignCount(activityId);
    }
}
