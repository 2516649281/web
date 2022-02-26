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
 * 教师端测试
 */
@SpringBootTest
public class teacherTest {

    @Autowired
    private WorkService workService;

    @Autowired
    private ClassService classService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentWorkService studentWorkService;

    @Autowired
    private StudentService studentService;

    /**
     * 教师端通过班级名称查询已布置的班级作业
     */
    @Test
    void selectAllByClassName() {
        String className = "计算机02";
        for (Class aClass : classService.list(new QueryWrapper<Class>().eq("class_Name", className))) {
            List<Work> classId = workService.list(new QueryWrapper<Work>().eq("class_Id", aClass.getClassId()));
            for (Work work : classId) {
                work.setTeacherName(teacherService.getOne(new QueryWrapper<Teacher>().eq("teacher_Id", work.getTeacherId())).getTeacherName());
            }
            aClass.setClassWorks(classId);
            System.out.println(aClass);
        }
    }

    /**
     * 教师端添加学生作业
     */
    @Test
    void addAllByClassName() {
        String className = "计算机02";
        String workName = "java";
        String workContent = "输出数组";
        Long teacherId = 51L;
        Work work = new Work();
        work.setWorkName(workName);
        work.setWorkContent(workContent);
        work.setTeacherId(teacherId);
        work.setClassId(classService.getOne(new QueryWrapper<Class>().eq("class_Name", className)).getClassId());
        System.out.println(workService.save(work));
    }

    /**
     * 教师端根据id删除(谨慎使用!)
     */
    @Test
    void delAllById() {
        Long workId = 68L;
        System.out.println(studentWorkService.remove(new QueryWrapper<StudentWork>().eq("work_Id", workId)));
        System.out.println(workService.removeById(workId));
    }

    /**
     * 教师端根据id修改
     */
    @Test
    void updateById() {
        Long workId = 68L;
        Long teacherId = 51L;
        String workName = "java";
        String workContent = "输出数组[1,5]";
        Work work = new Work();
        work.setWorkId(workId);
        work.setTeacherId(teacherId);
        work.setWorkName(workName);
        work.setWorkContent(workContent);
        System.out.println(workService.updateById(work));
    }

    /**
     * 教师端批改学生作业
     */
    @Test
    void updateSource() {
        StudentWork studentWork = new StudentWork();
        studentWork.setStudentSource(90.0);
        studentWork.setStudentWorkId(234L);
        System.out.println(studentWorkService.updateById(studentWork));
    }

    /**
     * 教师端根据班级名检查学生已完成的作业
     */
    @Test
    void selectWorkByClassName() {
        String className = "电商01";
        Long workId = 69L;
        for (Student student : studentService.list(new QueryWrapper<Student>().eq("class_Id", classService.getOne(new QueryWrapper<Class>().eq("class_Name", className)).getClassId()))) {
            for (StudentWork studentWork : studentWorkService.list(new QueryWrapper<StudentWork>().eq("student_Id", student.getStudentId()).eq("work_Id", workId))) {
                studentWork.setStudentName(student.getStudentName());
                System.out.println(studentWork);
            }
        }
    }
}
