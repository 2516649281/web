package com.chunfeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描Mapper
@MapperScan("com.chunfeng.dao.mapper")
@SpringBootApplication
public class StudentWorkSystemBehindPortApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentWorkSystemBehindPortApplication.class, args);
    }

}
