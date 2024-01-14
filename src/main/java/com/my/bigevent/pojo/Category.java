package com.my.bigevent.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;//主键ID
    @NotEmpty
    private String categoryName;//分类名称
    @NotEmpty
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")   // 规定时间格式
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")   // 规定时间格式
    private LocalDateTime updateTime;//更新时间


    // 分组校验
    // 如果某个校验没有指定分组，那么默认是 Default 分组
    // 分组之间可以继承，A extends B，那么A中拥有B中所有的校验项
    public interface Add extends Default
    {
        // 虽然暂时变量上，并没有使用到 Add.class ，但是这个目前是用于演示区分 Update.class 的效果
    }

    public interface Update extends Default
    {

    }
}
