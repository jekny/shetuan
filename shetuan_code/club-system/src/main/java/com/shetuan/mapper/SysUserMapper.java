package com.shetuan.mapper;

/**
 * ClassName: SysUserMapper
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 16:16
 * @Version 1.0
 */
import com.shetuan.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {
    SysUser selectByUsername(String username);
    int insert(SysUser user);
}
