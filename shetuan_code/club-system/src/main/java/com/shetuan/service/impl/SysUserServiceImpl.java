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
import org.springframework.util.DigestUtils;

@Service
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper userMapper;
    public SysUserServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public R<SysUser> login(String username, String password) {
        SysUser user = userMapper.selectByUsername(username);
        if(user == null) return R.fail("账号不存在");
        String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!user.getPassword().equals(md5Pwd)) return R.fail("密码错误");
        return R.ok(user);
    }

    @Override
    public R<String> register(SysUser user) {
        SysUser exist = userMapper.selectByUsername(user.getUsername());
        if(exist != null) return R.fail("学号已注册");
        String md5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5);
        userMapper.insert(user);
        return R.ok("注册成功");
    }

    @Override
    public SysUser getById(Long id) {
        return userMapper.selectById(id);
    }
}
