package com.chunfeng.Login.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chunfeng.dao.entity.User;
import com.chunfeng.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     */
    @Test
    void selectTest() {
        System.out.println(userService.list());
    }

    /**
     * 根据职务查询用户
     */
    @Test
    void selectAllByDutyTest() {
        System.out.println(userService.list(new QueryWrapper<User>().eq("user_duty", 2)));
    }
}
