package com.shetuan.controller;

/**
 * ClassName: ClubController
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:21
 * @Version 1.0
 */

import com.shetuan.entity.SysClub;
import com.shetuan.service.SysClubService;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {
    private final SysClubService clubService;
    public ClubController(SysClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/list")
    public R<List<SysClub>> listAll() {
        return clubService.getAllClub();
    }

    @GetMapping("/info/{id}")
    public R<SysClub> getInfo(@PathVariable Long id) {
        return clubService.getClubById(id);
    }

    @GetMapping("/my")
    public R<List<SysClub>> myClub(HttpServletRequest request) {
        Long uid = (Long) request.getAttribute("loginUserId");
        return clubService.getClubByLeader(uid);
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody SysClub club, HttpServletRequest request) {
        String role = (String) request.getAttribute("loginRole");
        if (!"ADMIN".equals(role)) return R.fail("仅管理员可创建社团");
        return clubService.addClub(club);
    }

    @PostMapping("/update")
    public R<String> update(@RequestBody SysClub club, HttpServletRequest request) {
        String role = (String) request.getAttribute("loginRole");
        Long uid = (Long) request.getAttribute("loginUserId");
        SysClub target = clubService.getClubById(club.getId()).getData();
        if (!"ADMIN".equals(role) && !target.getLeaderId().equals(uid)) {
            return R.fail("无权修改");
        }
        return clubService.updateClub(club);
    }
}
