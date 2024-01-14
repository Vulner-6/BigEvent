package com.my.bigevent.service.impl;

import com.my.bigevent.mapper.CategoryMapper;
import com.my.bigevent.pojo.Category;
import com.my.bigevent.service.CategoryService;
import com.my.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public void add(Category category)
    {
        // 补充属性值，数据库里规定 category 的字段都不为 null
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("id");
        category.setCreateUser(userId);

        categoryMapper.add(category);
    }

    @Override
    public List<Category> list()
    {
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId=(Integer) map.get("id");
        return categoryMapper.list(userId);
    }

    @Override
    public void update(Category category)
    {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id)
    {
        categoryMapper.delete(id);
    }
}
