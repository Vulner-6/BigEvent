package com.my.bigevent.service;

import com.my.bigevent.pojo.Category;

import java.util.List;

public interface CategoryService
{
    void add(Category category);

    List<Category> list();

    void update(Category category);

    void delete(Integer id);
}
