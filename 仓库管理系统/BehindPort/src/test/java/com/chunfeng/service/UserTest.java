package com.chunfeng.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chunfeng.entity.User;
import com.chunfeng.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户测试
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService user;

    /**
     * 查询所有用户
     */
    @Test
    public void selectAll() {
        user.list().forEach(System.out::println);
    }

    /**
     * 模糊查询用户
     */
    @Test
    public void selectAllByClassName() {
        String userName = "d";
        System.out.println(user.list(new QueryWrapper<User>().like("user_Name", userName)));
    }

    /**
     * 添加用户
     */
    @Test
    public void insertUser() {
        User user = new User();
        user.setUserName("root2");
        user.setUserPassword("123456");
        System.out.println(this.user.save(user));
    }

    /**
     * 修改用户
     */
    @Test
    public void updateUser() {
        User user = new User();
        user.setId(6L);
        user.setUserName("admin5");
        System.out.println(this.user.updateById(user));
    }

    /**
     * 冻结用户
     */
    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(6L);
        System.out.println(this.user.removeById(user.getId()));
    }

    /**
     * 解冻账号
     */
    @Test
    public void unfreezeUser() {
        User user = new User();
        user.setId(6L);
        System.out.println(this.user.UnfreezeUser(user));
    }
}
