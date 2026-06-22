package com.shetuan.controller;

/**
 * ClassName: UserController
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:20
 * @Version 1.0
 */

import com.shetuan.entity.SysUser;
import com.shetuan.service.SysUserService;
import com.shetuan.util.JwtUtil;
import com.shetuan.util.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final SysUserService userService;
    private final JwtUtil jwtUtil;
    public UserController(SysUserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public R<?> login(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");
        R<SysUser> res = userService.login(username,password);
        if(res.getCode() != 200) return res;
        SysUser user = res.getData();
        String token = jwtUtil.generateToken(user.getId(),user.getUsername(),user.getRole());
        return R.ok(token);
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody SysUser user){
        return userService.register(user);
    }
}
