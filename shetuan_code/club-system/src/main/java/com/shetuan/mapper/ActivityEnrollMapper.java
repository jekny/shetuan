package com.shetuan.mapper;

/**
 * ClassName: ActivityEnrollMapper
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 16:29
 * @Version 1.0
 */
import com.shetuan.entity.ActivityEnroll;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ActivityEnrollMapper {
    ActivityEnroll selectByActUser(Long activityId, Long userId);
    int insert(ActivityEnroll enroll);
    int deleteByActUser(Long activityId, Long userId);
    List<ActivityEnroll> selectByUserId(Long userId);
    List<ActivityEnroll> selectByActivityId(Long activityId);
    int countByActivity(Long activityId);
}