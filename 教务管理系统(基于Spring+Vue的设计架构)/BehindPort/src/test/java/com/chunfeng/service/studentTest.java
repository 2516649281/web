package com.chunfeng.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chunfeng.dao.entity.Class;
import com.chunfeng.dao.entity.*;
import com.chunfeng.service.inter.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 学生端测试
 */
@SpringBootTest
public class studentTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentWorkService studentWorkService;

    @Autowired
    private WorkService workService;

    @Autowired
    private TeacherService teacherService;


    /**
     * 查询所有(测试对象填充)
     */
    @Test
    void selectAllWork() {
        for (Student student : studentService.list()) {
            student.setClassName(classService.getOne(new QueryWrapper<Class>().eq("class_Id", student.getClassId())).getClassName());
            student.setStudentWorks(studentWorkService.list(new QueryWrapper<StudentWork>().eq("student_Id", student.getStudentId())));
            System.out.println(student);
        }
    }

    /**
     * 根据学生姓名查询
     */
    @Test
    void selectAllWorkByName() {
        String name = "小红";
        for (Student student : studentService.list(new QueryWrapper<Student>().eq("student_Name", name))) {
            student.setClassName(classService.getOne(new QueryWrapper<Class>().eq("class_Id", student.getClassId())).getClassName());
            List<StudentWork> studentId = studentWorkService.list(new QueryWrapper<StudentWork>().eq("student_Id", student.getStudentId()));
            student.setStudentWorks(studentId);
            System.out.println(student);
        }
    }

    /**
     * 提交作业
     */
    @Test
    void addWorkByName() {
        Long studentId = 201L;
        String studentWork = "完成1";
        Long workId = 69L;
        StudentWork studentWorks = new StudentWork();
        studentWorks.setStudentWork(studentWork);
        studentWorks.setWorkId(workId);
        studentWorks.setStudentId(studentId);
        System.out.println(studentWorkService.save(studentWorks));
    }

    /**
     * 根据id删除作业
     */
    @Test
    void delWorkByWorkId() {
        Long studentWorkId = 233L;
        System.out.println(studentWorkService.removeById(studentWorkId));
    }

    /**
     * 根据id值修改
     */
    @Test
    void updateWorkByWorkId() {
        Long workId = 5L;
        Long studentWorkId = 231L;
        String studentWork = "3";
        StudentWork studentWorks = new StudentWork();
        studentWorks.setWorkId(workId);
        studentWorks.setStudentWork(studentWork);
        studentWorks.setStudentWorkId(studentWorkId);
        System.out.println(studentWorkService.updateById(studentWorks));
    }

    /**
     * 根据班级查询作业编号
     */
    @Test
    void selectWorkByClassName() {
        String className = "计算机02";
//        for (Class aClass : classService.list(new QueryWrapper<Class>().eq("class_Name",className))) {
//            List<Work> list = workService.list(new QueryWrapper<Work>().eq("class_Id", aClass.getClassId()));
//            for (Work classId : list) {
//                classId.setTeacherName(teacherService.getOne(new QueryWrapper<Teacher>().eq("teacher_Id",classId.getTeacherId())).getTeacherName());
//            }
//            aClass.setClassWorks(list);
//            System.out.println(aClass);
//        }
        for (Work work : workService.list(new QueryWrapper<Work>().eq("class_Id", classService.getOne(new QueryWrapper<Class>().eq("class_Name", className)).getClassId()))) {
            work.setTeacherName(teacherService.getOne(new QueryWrapper<Teacher>().eq("teacher_Id", work.getTeacherId())).getTeacherName());
            System.out.println(work);
        }
    }
}