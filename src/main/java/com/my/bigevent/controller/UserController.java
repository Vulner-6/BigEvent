package com.my.bigevent.controller;

import com.my.bigevent.pojo.Result;
import com.my.bigevent.pojo.User;
import com.my.bigevent.service.UserService;
import com.my.bigevent.utils.JwtUtil;
import com.my.bigevent.utils.Md5Util;
import com.my.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController
{
    @Autowired
    UserService userService;

    /**
     * 注册功能
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")  // 这里的正则表达式，里面的转义字符需要两个，不同于直接的正则表达式。若不符合正则匹配规则，就会报异常，因此需要全局异常处理
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password)
    {

        // 查询用户
        User u=userService.findByUserName(username);
        if(u==null)
        {
            // 用户不存在，则注册
            userService.register(username,password);
            return Result.success("注册成功！");
        }
        else
        {
            // 用户存在，则返回提示信息
            return Result.error("用户已存在！");
        }
    }

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password)
    {
        // 根据用户名查询用户
        User loginUser=userService.findByUserName(username);
        // 若用户不存在，则返回结果
        if(loginUser==null)
        {
            return Result.error("用户不存在！");
        }
        // 若用户存在，则校验密码，密码正确返回jwt令牌
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword()))
        {
            // 生成 jwt 令牌，存入当前用户信息
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token= JwtUtil.genToken(claims);
            return Result.success(token);
        }

        // 若用户存在，但是密码校验不对
        return Result.error("密码错误！");
    }

    /**
     * 获取用户信息
     * @param
     * @return
     */
    @GetMapping("/userInfo")
    public Result<User> userInfo()
    {
        // 根据用户名查询用户
        Map<String,Object> map= ThreadLocalUtil.get();
        String username=(String)map.get("username");
        User user=userService.findByUserName(username);
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user)
    {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")   // @URL 限定当前字符串满足 URL 格式
    public Result updateAvatar(@RequestParam @URL String avatarUrl)
    {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params)
    {
        // 校验参数
        String oldPwd=params.get("old_pwd");
        String newPwd=params.get("new_pwd");
        String rePwd=params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd))
        {
            return Result.error("缺少参数！");
        }

        // 比对原密码是否正确
        Map<String,Object> map=ThreadLocalUtil.get();
        String username=(String) map.get("username");
        User loginUser=userService.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd)))
        {
            return Result.error("原密码错误！");
        }

        // 校验新密码与确认密码是否一致
        if(newPwd.equals(rePwd))
        {
            // 若原密码正确，且新密码与确认密码一致，则完成新密码的更新
            userService.updatePwd(newPwd);
            return Result.success();
        }
        else
        {
            return Result.error("新密码与确认密码不一致！");
        }
    }
}
