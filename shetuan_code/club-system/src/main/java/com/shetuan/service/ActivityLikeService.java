package com.shetuan.service;

/**
 * ClassName: ActivityLikeService
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:10
 * @Version 1.0
 */
import com.shetuan.util.R;

public interface ActivityLikeService {
    R<String> toggleLike(Long activityId, Long userId);
}
