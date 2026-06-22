package com.shetuan.service;

/**
 * ClassName: SysUserService
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:06
 * @Version 1.0
 */
import com.shetuan.entity.SysUser;
import com.shetuan.util.R;

public interface SysUserService {
    R<SysUser> login(String username, String password);
    R<String> register(SysUser user);
    SysUser getById(Long id);
}
