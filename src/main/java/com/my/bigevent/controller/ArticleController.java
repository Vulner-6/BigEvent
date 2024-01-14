package com.my.bigevent.controller;

import com.my.bigevent.pojo.Article;
import com.my.bigevent.pojo.PageBean;
import com.my.bigevent.pojo.Result;
import com.my.bigevent.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController
{
    @Autowired
    ArticleService articleService;

    /**
     * 新增文章
     * @param article
     * @return
     */
    @PostMapping
    public Result add(@RequestBody @Validated Article article)
    {
        articleService.add(article);
        return Result.success();
    }

    /**
     * 获取文章列表
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    @GetMapping   // 这里的 PageBean 是事先定义好的实体类
    public Result<PageBean<Article>> list(Integer pageNum,Integer pageSize,
                                          @RequestParam(required = false) Integer categoryId,
                                          @RequestParam(required = false) String state)
    {
        PageBean<Article> pb=articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }


}
