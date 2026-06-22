package com.shetuan.service;

/**
 * ClassName: ActivityReviewService
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:09
 * @Version 1.0
 */
import com.shetuan.entity.ActivityReview;
import com.shetuan.util.R;

public interface ActivityReviewService {
    R<String> saveReview(ActivityReview review);
    R<ActivityReview> getReviewByActivity(Long activityId);
}
