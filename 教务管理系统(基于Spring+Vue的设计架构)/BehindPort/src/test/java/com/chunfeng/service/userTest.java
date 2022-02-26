package com.chunfeng.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chunfeng.dao.entity.Student;
import com.chunfeng.dao.entity.Teacher;
import com.chunfeng.dao.entity.User;
import com.chunfeng.service.inter.StudentService;
import com.chunfeng.service.inter.TeacherService;
import com.chunfeng.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 用户管理
 */
@SpringBootTest
public class userTest {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 已知账号登录验证并判断用户身份
     */
    @Test
    void LoginTest() {
        String username = "B20211";
        String password = "1234567";
        List<User> userList = userService.list(new QueryWrapper<User>().eq("user_Name", username).eq("user_password", password));
        try {
            for (User user : userList) {
                String name;
                if (user.getUserIdentity() == 0) {
                    name = studentService.getOne(new QueryWrapper<Student>().eq("user_Id", user.getUserId())).getStudentName();
                } else {
                    name = teacherService.getOne(new QueryWrapper<Teacher>().eq("user_Id", user.getUserId())).getTeacherName();
                }
                int userIdentity = user.getUserIdentity();
                System.out.println("登录成功,欢迎你" + name + ",您的身份是" + (userIdentity == 0 ? "学生" : "教师") + "," + "您" + (user.getUserAdmin() != 0 ? "是" : "不是") + "管理员");
            }
            if (userList.size() == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("登录失败,请检查账号或密码是否正确");
            e.printStackTrace();
        }
    }

    /**
     * 注册账号
     */
    @Test
    void RegisterTest() {
        String username = "admin";
        String password = "1234567";
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        try {
            if (userService.list(new QueryWrapper<User>().eq("user_Name", username)).size() == 0) {
                if (userService.save(user)) {
                    System.out.println("欢迎注册，请牢记账号和密码，若有遗失请立即联系管理员重置密码!");
                }
            } else {
                System.out.println("该用户名可能已经存在，请更改后重试!");
            }
        } catch (Exception e) {
            System.out.println("注册失败，请检查字段名是否符合规范");
            e.printStackTrace();
        }
    }

    /**
     * 查询所有
     */
    @Test
    void selectAllUser(){
//        List<User> users = userService.selectAllUserPage(new Page<>(1,5)).getRecords();
//        users.forEach(System.out::println);
        List<User> userList = userService.selectUserWrapper(new Page<>(1, 5), new QueryWrapper<User>().like("user_Name", "23")).getRecords();
        userList.forEach(System.out::println);
    }
}
