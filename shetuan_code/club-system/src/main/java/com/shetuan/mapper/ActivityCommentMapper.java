package com.shetuan.mapper;

/**
 * ClassName: ActivityCommentMapper
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 16:30
 * @Version 1.0
 */
import com.shetuan.entity.ActivityComment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ActivityCommentMapper {
    List<ActivityComment> selectByActivityId(Long activityId);
    int insert(ActivityComment comment);
    int updateTopById(Long id, Integer top);
    int deleteById(Long id);
}
