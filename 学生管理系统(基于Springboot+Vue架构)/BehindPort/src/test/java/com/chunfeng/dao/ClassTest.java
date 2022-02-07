package com.chunfeng.dao;

import com.chunfeng.service.inter.ClassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClassTest {

    @Autowired
    private ClassService classService;

    @Test
    void selectAll(){
        classService.list().forEach(System.out::println);
    }
}
