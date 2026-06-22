package com.shetuan.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * ClassName: SysUser
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 12:25
 * @Version 1.0
 */
@Data
public class SysUser {
    private Long id;
    private String username;
    private String password;
    private String realName;
    private String role;
    private LocalDateTime createTime;
}