package com.shetuan.service.impl;

/**
 * ClassName: ActivityReviewServiceImpl
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:18
 * @Version 1.0
 */

import com.shetuan.entity.ActivityReview;
import com.shetuan.mapper.ActivityReviewMapper;
import com.shetuan.service.ActivityReviewService;
import com.shetuan.util.R;
import org.springframework.stereotype.Service;

@Service
public class ActivityReviewServiceImpl implements ActivityReviewService {
    private final ActivityReviewMapper reviewMapper;
    public ActivityReviewServiceImpl(ActivityReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public R<String> saveReview(ActivityReview review) {
        ActivityReview old = reviewMapper.selectByActivityId(review.getActivityId());
        if (old == null) {
            reviewMapper.insert(review);
        } else {
            reviewMapper.updateById(review);
        }
        return R.ok("活动回顾保存成功");
    }

    @Override
    public R<ActivityReview> getReviewByActivity(Long activityId) {
        ActivityReview review = reviewMapper.selectByActivityId(activityId);
        return R.ok(review);
    }
}
