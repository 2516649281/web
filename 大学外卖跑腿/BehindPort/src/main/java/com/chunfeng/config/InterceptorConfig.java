package com.chunfeng.config;

import com.chunfeng.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 将登录拦截器注册到springboot容器中
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 注册登录拦截器
     *
     * @param registry 拦截器仓库
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/account/login",//登录
                        "/account/register",//注册
                        "/**/*.html",       //html静态资源
                        "/**/*.js",         //js静态资源
                        "/**/*.css",        //css静态资源
                        "/**/*.img");       //图片资源
    }
}