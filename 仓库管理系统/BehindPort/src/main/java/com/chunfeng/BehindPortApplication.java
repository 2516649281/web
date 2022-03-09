package com.chunfeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chunfeng.dao")
public class BehindPortApplication {

    public static void main(String[] args) {
        SpringApplication.run(BehindPortApplication.class, args);
    }

}
