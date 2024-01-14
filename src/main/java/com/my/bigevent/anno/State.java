package com.my.bigevent.anno;

import com.my.bigevent.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// 可以根据已有的注解进行仿写
@Documented   // 表示形成帮助文档的时候，生成当前注解信息
@Constraint(
        validatedBy = {StateValidation.class}  // 这里指定 StateValidation.class 作为校验规则
)   // 指定通过哪个类定义注解的校验逻辑
@Target({ElementType.FIELD})  // 表示注解可以使用的对象，这里是字段
@Retention(RetentionPolicy.RUNTIME)  // 表示注解持续作用到程序运行时
public @interface State
{
    // 提供注解校验失败后的提示信息
    String message() default "state参数的值，只能是已发布或者草稿！";

    // 指定分组
    Class<?>[] groups() default {};

    // 负载，获取 state 注解附加信息，目前用不到
    Class<? extends Payload>[] payload() default {};
}
