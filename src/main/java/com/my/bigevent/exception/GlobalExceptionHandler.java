package com.my.bigevent.exception;

import com.my.bigevent.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 用于捕获所有 Controller 中的异常，并返回 json 格式字符串
public class GlobalExceptionHandler
{
    @ExceptionHandler(Exception.class)   // 所有类型的异常
    public Result handleException(Exception e)
    {
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败！");
    }
}
