package com.shetuan.controller;

/**
 * ClassName: ReviewController
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:23
 * @Version 1.0
 */

import com.shetuan.entity.ActivityReview;
import com.shetuan.service.ActivityReviewService;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ActivityReviewService reviewService;
    public ReviewController(ActivityReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public R<String> save(@RequestBody ActivityReview review, HttpServletRequest request) {
        String role = (String) request.getAttribute("loginRole");
        if (!"LEADER".equals(role) && !"ADMIN".equals(role)) {
            return R.fail("仅负责人可上传活动回顾");
        }
        return reviewService.saveReview(review);
    }

    @GetMapping("/{activityId}")
    public R<ActivityReview> get(@PathVariable Long activityId) {
        return reviewService.getReviewByActivity(activityId);
    }
}
