package com.chunfeng.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chunfeng.dao.pojo.Class;
import com.chunfeng.dao.pojo.Json;
import com.chunfeng.dao.pojo.Student;
import com.chunfeng.service.inter.ClassService;
import com.chunfeng.service.inter.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生管理
 */
@SpringBootTest
public class StudentTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;

    /**
     * 查看所有学生
     */
    @Test
    void select() {
        for (Student student : studentService.list()) {
            int classId = student.getClassId();
            String className = classService.getById(classId).getClassName();
            student.setClassName(className);
            System.out.println(student);
        }
    }

    /**
     * 添加学生
     */
    @Test
    void insert() {
        Student student = new Student();
        student.setClassName("计算机01");//班级名称，这里传入即可
        student.setStudentName("芙蓉");
        student.setStudentAge(19);
        student.setStudentSex("女");
        student.setStudentAddress("南京");
        student.setClassId(classService.getOne(new QueryWrapper<Class>().select("Id").eq("Class_Name", student.getClassName())).getId());//获取班级id，不参与显示
        System.out.println(studentService.save(student));
    }

    /**
     * 删除学生
     */
    @Test
    void delete() {
        int id = 5;
        boolean b = studentService.removeById(id);
        System.out.println(b);
    }

    /**
     * 修改学生
     */
    @Test
    void update() {
        Student student = new Student();
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        student.setId(6);
        student.setStudentName("小容");
        student.setStudentAge(19);
        student.setStudentSex("女");
        student.setStudentAddress("广东");
        student.setClassName("护理01");
        queryWrapper.select("Id").eq("Class_Name", student.getClassName());
        int id = classService.getOne(queryWrapper).getId();
        student.setClassId(id);
//        System.out.println(student);
        System.out.println(studentService.updateById(student));
    }

    /**
     * 分页查询+模糊查询
     */
    @Test
    void PageSelect() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("Student_Name", "小");
        Page<Student> page = new Page<>(2, 2);
        studentService.page(page, queryWrapper);
        page.getRecords().forEach(System.out::println);
    }

    /**
     * 模糊查询+全部字段
     */
    @Test
    void likeSelect() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("Student_Name", "明");
        for (Student student : studentService.list(queryWrapper)) {
            int classId = student.getClassId();
            String className = classService.getById(classId).getClassName();
            student.setClassName(className);
            System.out.println(student);
        }
    }

    /**
     * 完整查询
     */
    @Test
    void PageAll() {
        Page<Student> studentPage=studentService.page(new Page<>(2, 1));
        List<Student> students = studentPage.getRecords();
        for (Student student :  students){
            student.setClassName(classService.getById(student.getClassId()).getClassName());//先根据班级id查询班级名称，再将班级名称装载Student对象中
            student.setSize(studentPage.getTotal());
        }
        students.forEach(System.out::println);
    }

    /**
     * 根据班级名称查找学生
     */
    @Test
    void selectByClassName() {
        String className="护理01";
        List<Student> list = studentService.list(new QueryWrapper<Student>().eq("Class_Id", classService.getOne(new QueryWrapper<Class>().select("Id").eq("Class_Name", className)).getId()));
        for (Student student : list) {
            student.setClassName(className);
        }
        list.forEach(System.out::println);
    }

}

