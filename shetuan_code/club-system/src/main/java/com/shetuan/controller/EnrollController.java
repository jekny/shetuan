package com.shetuan.controller;

/**
 * ClassName: EnrollController
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:22
 * @Version 1.0
 */

import com.shetuan.service.ActivityEnrollService;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enroll")
public class EnrollController {
    private final ActivityEnrollService enrollService;
    public EnrollController(ActivityEnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @PostMapping("/add/{activityId}")
    public R<String> enroll(@PathVariable Long activityId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("loginUserId");
        return enrollService.enroll(activityId, userId);
    }

    @PostMapping("/cancel/{activityId}")
    public R<String> cancelEnroll(@PathVariable Long activityId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("loginUserId");
        return enrollService.cancelEnroll(activityId, userId);
    }

    @GetMapping("/my")
    public R<?> myEnroll(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("loginUserId");
        return enrollService.getMyEnroll(userId);
    }

    @GetMapping("/export/{activityId}")
    public void exportExcel(@PathVariable Long activityId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("loginUserId");
        String role = (String) request.getAttribute("loginRole");
        enrollService.exportExcel(activityId, userId, role, request);
    }
}