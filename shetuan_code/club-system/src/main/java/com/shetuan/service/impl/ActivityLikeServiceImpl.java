package com.shetuan.service.impl;

/**
 * ClassName: ActivityLikeServiceImpl
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:18
 * @Version 1.0
 */

import com.shetuan.entity.ActivityLike;
import com.shetuan.mapper.ActivityLikeMapper;
import com.shetuan.mapper.ActivityReviewMapper;
import com.shetuan.service.ActivityLikeService;
import com.shetuan.util.R;
import org.springframework.stereotype.Service;

@Service
public class ActivityLikeServiceImpl implements ActivityLikeService {
    private final ActivityLikeMapper likeMapper;
    private final ActivityReviewMapper reviewMapper;

    public ActivityLikeServiceImpl(ActivityLikeMapper likeMapper, ActivityReviewMapper reviewMapper) {
        this.likeMapper = likeMapper;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public R<String> toggleLike(Long activityId, Long userId) {
        ActivityLike exist = likeMapper.selectByActUser(activityId, userId);
        if (exist != null) {
            likeMapper.deleteByActUser(activityId, userId);
            return R.ok("取消点赞");
        } else {
            ActivityLike like = new ActivityLike();
            like.setActivityId(activityId);
            like.setUserId(userId);
            likeMapper.insert(like);
            reviewMapper.addLikeNum(activityId);
            return R.ok("点赞成功");
        }
    }
}