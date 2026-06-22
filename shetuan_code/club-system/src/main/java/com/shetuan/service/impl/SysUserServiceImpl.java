package com.shetuan.service.impl;

/**
 * ClassName: SysUserServiceImpl
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:14
 * @Version 1.0
 */

import com.shetuan.entity.SysUser;
import com.shetuan.mapper.SysUserMapper;
import com.shetuan.service.SysUserService;
import com.shetuan.util.R;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper userMapper;
    public SysUserServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 登录：取消MD5，直接明文对比
    @Override
    public R<SysUser> login(String username, String password) {
        SysUser user = userMapper.selectByUsername(username);
        if(user == null) return R.fail("账号不存在");
        // 直接用前端输入的明文 和数据库明文密码比对，去掉MD5加密
        if(!user.getPassword().equals(password)) return R.fail("密码错误");
        return R.ok(user);
    }

    // 注册：取消MD5加密，直接存明文密码
    @Override
    public R<String> register(SysUser user) {
        SysUser exist = userMapper.selectByUsername(user.getUsername());
        if(exist != null) return R.fail("学号已注册");
        // 不再加密，直接把前端输入的原始密码存入数据库
        userMapper.insert(user);
        return R.ok("注册成功");
    }

    @Override
    public SysUser getById(Long id) {
        return userMapper.selectById(id);
    }
}
