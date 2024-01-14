package com.my.bigevent.validation;

import com.my.bigevent.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
// 定义 State 注解具体的逻辑与功能
public class StateValidation implements ConstraintValidator<State,String>
{
    // 重写方法，提供校验规则
    // String s 是将来要校验的数据
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        // 提供校验规则
        if(s==null)
        {
            // 返回 false 表示校验不通过
            return false;
        }
        if(s.equals("yes")||s.equals("no"))
        {
            // 返回 true，表示校验通过
            return true;
        }
        return false;

    }
}
