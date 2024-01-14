package com.my.bigevent.config;

import com.my.bigevent.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类，里面注册了自定义的拦截器。并没有使用 filter 过滤器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        // 登录接口，注册接口，不拦截
        // 将自定义的拦截器注册进来
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register");
    }
}
