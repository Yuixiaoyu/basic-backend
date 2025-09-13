package com.xiaoyu.basic.controller;

import com.xiaoyu.basic.common.BaseResponse;
import com.xiaoyu.basic.common.ResultUtils;
import com.xiaoyu.basic.service.impl.MinioService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/minio")
public class MinioController {

    @Resource
    private MinioService minioService;

    // 单文件上传：返回永久访问URL
    @PostMapping("/upload")
    public BaseResponse<String> upload(@RequestParam MultipartFile file) throws Exception {
        String fileUrl = minioService.uploadFile(file);
        return ResultUtils.success(fileUrl);
    }

    // 多文件上传：返回永久访问URL列表
    @PostMapping("/upload/batch")
    public BaseResponse<List<String>> uploadBatch(@RequestParam List<MultipartFile> files) throws Exception {
        List<String> fileUrlList = minioService.uploadFiles(files);
        return ResultUtils.success(fileUrlList);
    }

    // 删除单个文件
    @DeleteMapping("/delete")
    public String deleteFile(@RequestParam String filePath) throws Exception {
        minioService.deleteFile(filePath);
        return "Deleted: " + filePath;
    }

    // 批量删除文件
    @DeleteMapping("/delete/batch")
    public String deleteFiles(@RequestBody List<String> filePaths) throws Exception {
        minioService.deleteFiles(filePaths);
        return "Deleted files: " + filePaths;
    }
}
