/**
 * @Description TODO
 * @Classname CommonController
 * @Date 2024/4/2 11:19
 * @Created by Mingkai Feng
 */
package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * @author Mingkai Feng
     * @date 2024/4/2 11:28
     * @Description ToDo  文件上传
     * @param file
     * @return Result<String>
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传:{}", file.getOriginalFilename());
        String filePath = null;
        try {
            String originalFilename = file.getOriginalFilename();
            String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + extention;

            log.info("最终filePath:{}", filePath);
            filePath = aliOssUtil.upload(file.getBytes(), fileName);
            return Result.success(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.error("文件上传失败");
    }
}
