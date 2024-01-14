package com.my.bigevent.controller;

import com.my.bigevent.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
public class FileUploadController
{
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception
    {
        // 获取文件名
        String originalFilename=file.getOriginalFilename();
        // 保证文件名字是唯一的，从而防止文件被覆盖
        String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        // windows 系统写法，未考虑到 linux 系统情况
        file.transferTo(new File("C:\\Users\\Vulner-6\\Desktop\\Temp\\"+filename));
        return Result.success("http://test.com");
    }
}
