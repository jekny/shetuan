package com.shetuan.mapper;

/**
 * ClassName: SysClubMapper
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 16:26
 * @Version 1.0
 */
import com.shetuan.entity.SysClub;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysClubMapper {
    List<SysClub> selectAll();
    SysClub selectById(Long id);
    List<SysClub> selectByLeaderId(Long leaderId);
    int insert(SysClub club);
    int updateById(SysClub club);
}
