package com.my.bigevent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.my.bigevent.mapper.ArticleMapper;
import com.my.bigevent.pojo.Article;
import com.my.bigevent.pojo.PageBean;
import com.my.bigevent.service.ArticleService;
import com.my.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService
{
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void add(Article article)
    {
        // 补充属性
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    /**
     * 条件分页列表查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state)
    {
        // 创建 PageBean 对象
        PageBean<Article> pb=new PageBean<>();

        // 开启分页查询
        PageHelper.startPage(pageNum,pageSize);

        // 调用 mapper
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("id");
        List<Article> as=articleMapper.list(userId,categoryId,state);
        // Page 中提供了方法，可以获取 PageHelper 分页查询后，得到的总记录条数和当前页数据
        Page<Article> p=(Page<Article>) as;

        // 把数据填充到 PageBean 对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        return pb;
    }
}
