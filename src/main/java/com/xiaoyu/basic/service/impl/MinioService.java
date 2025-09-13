package com.xiaoyu.basic.service.impl;

import io.minio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    @Value("${minio.endpoint}")
    private String endpoint;

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    // 上传单文件并返回永久链接
    public String uploadFile(MultipartFile file) throws Exception {
        ensureBucketExists();

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        // 永久链接 (公共桶)
        return endpoint + "/" + bucket + "/" + fileName;
    }

    // 上传多文件并返回永久链接列表
    public List<String> uploadFiles(List<MultipartFile> files) throws Exception {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            urls.add(uploadFile(file));
        }
        return urls;
    }

    // 确保 bucket 存在
    private void ensureBucketExists() throws Exception {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    // 删除单个文件
    public void deleteFile(String filePath) throws Exception {
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket)
                        .object(fileName)
                        .build()
        );
    }

    // 删除多个文件
    public void deleteFiles(List<String> filePathList) throws Exception {
        List<String> fileNames = filePathList.stream()
                .map(filePath -> filePath.substring(filePath.lastIndexOf("/") + 1))
                .toList();
        for (String fileName : fileNames) {
            deleteFile(fileName);
        }
    }
}
