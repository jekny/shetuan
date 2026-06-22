package com.shetuan.mapper;

/**
 * ClassName: StatMapper
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 16:32
 * @Version 1.0
 */
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;
import java.util.List;

@Mapper
public interface StatMapper {
    // 负责人：当前社团所有活动报名、签到数据
    List<Map<String,Object>> selectLeaderActivityStat(Long clubId);
    // 管理员：平台全部统计
    Map<String,Object> selectAdminAllStat();
}
