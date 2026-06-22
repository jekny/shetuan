package com.shetuan.controller;

/**
 * ClassName: LikeController
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:24
 * @Version 1.0
 */

import com.shetuan.service.ActivityLikeService;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final ActivityLikeService likeService;
    public LikeController(ActivityLikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/add/{activityId}")
    public R<String> toggle(@PathVariable Long activityId, HttpServletRequest request) {
        Long uid = (Long) request.getAttribute("loginUserId");
        return likeService.toggleLike(activityId, uid);
    }
}
