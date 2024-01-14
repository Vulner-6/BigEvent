package com.my.bigevent.service.impl;

import com.my.bigevent.mapper.UserMapper;
import com.my.bigevent.pojo.User;
import com.my.bigevent.service.UserService;
import com.my.bigevent.utils.Md5Util;
import com.my.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserMapper userMapper;
    @Override
    public User findByUserName(String username)
    {
        User u=userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password)
    {
        // 密码先加密，再添加至数据库
        String md5String= Md5Util.getMD5String(password);
        // 添加至数据库
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user)
    {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl)
    {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd)
    {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
