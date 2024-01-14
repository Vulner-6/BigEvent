package com.my.bigevent.interceptors;

import com.my.bigevent.utils.JwtUtil;
import com.my.bigevent.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 自定义拦截器，用于校验身份
 */
@Component
public class LoginInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        // 获得令牌
        String token=request.getHeader("Authorization");
        // 令牌验证
        try
        {
            // 解析 jwt token，若无异常，则说明解析成功，放行
            Map<String,Object> claims= JwtUtil.parseToken(token);
            // 将业务数据存放到 ThreadLocl 中
            ThreadLocalUtil.set(claims);
            return true;
        }
        catch (Exception e)
        {
            // 设置状态码为401，不放行
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        // 每次请求结束后，清除 ThreadLocal 里的数据，否则会导致内存泄露问题！
        ThreadLocalUtil.remove();
    }
}
