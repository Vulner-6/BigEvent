package com.my.bigevent.mapper;

import com.my.bigevent.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper
{
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)"+
                        "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime}," +
            "#{updateTime})")
    void add(Article article);

    // 动态实现 sql，所以这里不用注解
    List<Article> list(Integer userId, Integer categoryId, String state);
}
