package com.shetuan.mapper;

/**
 * ClassName: ActivityLikeMapper
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 16:31
 * @Version 1.0
 */
import com.shetuan.entity.ActivityLike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityLikeMapper {
    ActivityLike selectByActUser(Long activityId, Long userId);
    int insert(ActivityLike like);
    int deleteByActUser(Long activityId, Long userId);
}
