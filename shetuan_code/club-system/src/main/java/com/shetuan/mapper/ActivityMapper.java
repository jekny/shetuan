package com.shetuan.mapper;

/**
 * ClassName: ActivityMapper
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 16:41
 * @Version 1.0
 */
import com.shetuan.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ActivityMapper {
    List<Activity> selectList(Activity activity);
    Activity selectById(Long id);
    int insert(Activity activity);
    int updateById(Activity activity);
}
