package com.shetuan.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * ClassName: JwtUtil
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 12:13
 * @Version 1.0
 */
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private Long expire;

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 生成token
    public String generateToken(Long userId, String username, String role) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("username", username)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 解析token
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 获取用户id
    public Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    // 获取角色
    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }
}
