package com.chunfeng.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chunfeng.dao.entity.Class;
import com.chunfeng.dao.entity.Student;
import com.chunfeng.dao.entity.Teacher;
import com.chunfeng.dao.entity.User;
import com.chunfeng.service.inter.ClassService;
import com.chunfeng.service.inter.StudentService;
import com.chunfeng.service.inter.TeacherService;
import com.chunfeng.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 管理员测试
 */
@SpringBootTest
public class adminTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassService classService;

    /**
     * 查询所有学生的信息
     */
    @Test
    void selectAllStudent() {
        for (Student student : studentService.page(new Page<>(1, 5)).getRecords()) {
            student.setClassName(classService.getOne(new QueryWrapper<Class>().eq("class_Id", student.getClassId())).getClassName());
            System.out.println(student);
        }
    }

    /**
     * 根据班级查询所有学生的信息
     */
    @Test
    void selectAllStudentByClassName() {
        String className = "计算机01";
        try {
            for (Student student : studentService.page(new Page<>(1, 20), new QueryWrapper<Student>().eq("class_Id", classService.getOne(new QueryWrapper<Class>().eq("class_Name", className)).getClassId())).getRecords()) {
                student.setClassName(className);
                System.out.println(student);
            }
        } catch (Exception e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
    }

    /**
     * 添加学生信息
     */
    @Test
    void addStudent() {
        Student student = new Student();
        student.setClassName("计算机01");
        student.setClassId(classService.getOne(new QueryWrapper<Class>().eq("class_Name", student.getClassName())).getClassId());
        student.setStudentName("小花");
        student.setStudentAge(19);
        student.setStudentSex("女");
        student.setStudentAddress("上海");
        System.out.println(studentService.save(student));
    }

    /**
     * 修改学生信息
     */
    @Test
    void updateStudent() {
        Student student = new Student();
        student.setStudentName("小花");
        student.setStudentAge(19);
        student.setStudentSex("女");
        student.setStudentAddress("重庆");
        student.setStudentId(205L);
        System.out.println(studentService.updateById(student));
    }

    /**
     * 删除学生信息
     */
    @Test
    void delStudent() {
        Long studentId = 205L;
        System.out.println(studentService.remove(new QueryWrapper<Student>().eq("student_Id", studentId)));
    }

    /**
     * 查看所有教师
     */
    @Test
    void selectAllTeacher() {
        teacherService.page(new Page<>(2, 20)).getRecords().forEach(System.out::println);
    }

    /**
     * 根据地区名查询
     */
    @Test
    void selectAllTeacherByAddress() {
        teacherService.page(new Page<>(1, 20), new QueryWrapper<Teacher>().eq("teacher_Address", "Huádōng")).getRecords().forEach(System.out::println);
    }

    /**
     * 添加教师信息
     */
    @Test
    void addTeacher() {
        Teacher teacher = new Teacher();
        teacher.setTeacherName("小鹿老师");
        teacher.setTeacherAge(35);
        teacher.setTeacherSex("女");
        teacher.setTeacherAddress("广东");
        System.out.println(teacherService.save(teacher));
    }

    /**
     * 修改教师信息
     */
    @Test
    void updateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(53L);
        teacher.setTeacherName("小鹿老师");
        teacher.setTeacherAddress("湖北");
        teacher.setTeacherAge(35);
        System.out.println(teacherService.updateById(teacher));
    }

    /**
     * 删除教师信息
     */
    @Test
    void delTeacherById() {
        Long teacherId = 53L;
        System.out.println(teacherService.removeById(teacherId));
    }

    /**
     * 查询所有用户信息
     */
    @Test
    void selectAllUser() {
        userService.page(new Page<>(1, 20)).getRecords().forEach(System.out::println);
    }

    /**
     * 根据用户名查询
     */
    @Test
    void selectAllUserByUserName() {
        String userName = "B20201";
        System.out.println(userService.getOne(new QueryWrapper<User>().eq("user_Name", userName)));
    }

    /**
     * 冻结账号
     */
    @Test
    void delUserByUserName() {
        String userName = "B20211";
        System.out.println(userService.remove(new QueryWrapper<User>().eq("user_Name", userName)));
    }

    /**
     * 解冻账号
     */
    @Test
    void UnfreezeUserTest() {
//        String userName = "B20211";
//        System.out.println(userService.UnfreezeUser(userName));
    }
}
