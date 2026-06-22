package com.shetuan.mapper;

/**
 * ClassName: ActivitySignMapper
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 16:30
 * @Version 1.0
 */
import com.shetuan.entity.ActivitySign;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ActivitySignMapper {
    ActivitySign selectByActUser(Long activityId, Long userId);
    int insert(ActivitySign sign);
    List<ActivitySign> selectByActivityId(Long activityId);
    int countByActivity(Long activityId);
}
