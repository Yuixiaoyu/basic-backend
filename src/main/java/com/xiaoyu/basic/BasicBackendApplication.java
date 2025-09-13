package com.xiaoyu.basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoyu.basic.mapper")
public class BasicBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasicBackendApplication.class, args);
    }

}
