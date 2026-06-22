package com.shetuan.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shetuan.util.JwtUtil;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: JwtInterceptor
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 12:22
 * @Version 1.0
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        // 放行登录注册接口
        String path = request.getRequestURI();
        if(path.contains("/user/login") || path.contains("/user/register")){
            return true;
        }
        if(token == null || token.isEmpty()){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(R.fail(401,"未登录，请先登录")));
            return false;
        }
        try {
            // 解析token存入request域供controller获取
            Long userId = jwtUtil.getUserId(token);
            String role = jwtUtil.getRole(token);
            request.setAttribute("loginUserId",userId);
            request.setAttribute("loginRole",role);
        }catch (Exception e){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(R.fail(401,"token失效，请重新登录")));
            return false;
        }
        return true;
    }
}
