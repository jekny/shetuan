package com.shetuan.controller;

/**
 * ClassName: StatController
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:24
 * @Version 1.0
 */

import com.shetuan.service.SysClubService;
import com.shetuan.service.StatService;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stat")
public class StatController {
    private final StatService statService;
    private final SysClubService clubService;

    public StatController(StatService statService, SysClubService clubService) {
        this.statService = statService;
        this.clubService = clubService;
    }

    @GetMapping("/leader")
    public R<List<Map<String, Object>>> leaderStat(HttpServletRequest request) {
        Long uid = (Long) request.getAttribute("loginUserId");
        String role = (String) request.getAttribute("loginRole");
        if (!"LEADER".equals(role)) return R.fail("仅社团负责人可查看");
        var clubRes = clubService.getClubByLeader(uid);
        if (clubRes.getData().isEmpty()) return R.fail("暂无管理社团");
        Long clubId = clubRes.getData().get(0).getId();
        return statService.getLeaderStat(clubId);
    }

    @GetMapping("/admin")
    public R<Map<String, Object>> adminStat(HttpServletRequest request) {
        String role = (String) request.getAttribute("loginRole");
        if (!"ADMIN".equals(role)) return R.fail("仅管理员可查看");
        return statService.getAdminStat();
    }
}