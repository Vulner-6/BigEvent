package com.my.bigevent.controller;

import com.my.bigevent.pojo.Category;
import com.my.bigevent.pojo.Result;
import com.my.bigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController
{
    @Autowired
    CategoryService categoryService;

    /**
     * 新增文章分类
     * @param category
     * @return
     */
    @PostMapping
    public Result add(@RequestBody @Validated Category category)
    {
        categoryService.add(category);
        return Result.success("新增分类成功！");
    }

    /**
     * 获取文章列表
     * @return
     */
    @GetMapping
    public Result<List<Category>> list()
    {
        List<Category> categoryList=categoryService.list();
        return Result.success(categoryList);
    }

    /**
     * 更新文章分类
     * @param category
     * @return
     */
    @PutMapping                       //指定分组校验中的 Update.class 分组进行校验
    public Result update(@RequestBody @Validated(Category.Update.class) Category category)
    {
        categoryService.update(category);
        return Result.success("更新文章分类成功！");
    }

    /**
     * 删除文章分类
     * @param id
     * @return
     */
    @DeleteMapping
    public Result delete(Integer id)
    {
        categoryService.delete(id);
        return Result.success("文章删除成功！");
    }

}
