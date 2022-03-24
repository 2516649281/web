package com.chunfeng.interceptor;

import com.chunfeng.util.JwtToken;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");//获取前台传回来的token
        if (token != null) {
            return JwtToken.checkToken(token);
        } else {//不存在则证明用户未登录
            return false;
        }
    }
}
