package com.shetuan.mapper;

/**
 * ClassName: ActivityReviewMapper
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 16:31
 * @Version 1.0
 */
import com.shetuan.entity.ActivityReview;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityReviewMapper {
    ActivityReview selectByActivityId(Long activityId);
    int insert(ActivityReview review);
    int updateById(ActivityReview review);
    int addLikeNum(Long activityId);
}
