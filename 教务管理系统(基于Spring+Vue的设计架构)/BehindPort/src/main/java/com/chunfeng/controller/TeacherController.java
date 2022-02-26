package com.chunfeng.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chunfeng.config.Json;
import com.chunfeng.dao.entity.Class;
import com.chunfeng.dao.entity.*;
import com.chunfeng.service.inter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师端
 */
@RestController
@RequestMapping("/teacherPost")
public class TeacherController {
    /**
     * 状态码
     */
    private boolean status;

    /**
     * 查询结果数
     */
    private Long size;

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
     *
     * @param className 班级名称
     * @param current   页码
     * @param size      每页显示数
     * @return Json
     */
    @GetMapping("/{current}/{size}/{className}")
    public Json selectAllByClassName(@PathVariable String className, @PathVariable int current, @PathVariable int size) {
        ArrayList<Class> data = new ArrayList<>();
        try {
            for (Class aClass : classService.list(new QueryWrapper<Class>().eq("class_Name", className))) {
                Page<Work> workPage = workService.page(new Page<>(current, size), new QueryWrapper<Work>().eq("class_Id", aClass.getClassId()));
                this.size = workPage.getTotal();//获取结果数
                List<Work> classId = workPage.getRecords();//包装作业信息
                for (Work work : classId) {
                    work.setTeacherName(teacherService.getOne(new QueryWrapper<Teacher>().eq("teacher_Id", work.getTeacherId())).getTeacherName());//将教师信息添加至作业表
                }
                aClass.setClassWorks(classId);
                data.add(aClass);
                status = true;
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 教师端根据班级名指定检查学生已完成的作业
     *
     * @param current   页码
     * @param size      每页显示数
     * @param className 班级名称
     * @param workId    作业编号
     * @return Json
     */
    @GetMapping("/{current}/{size}/{className}/{workId}")
    public Json selectAllByClassNameYesWork(@PathVariable int current, @PathVariable int size, @PathVariable String className, @PathVariable Long workId) {
        ArrayList<Student> data = new ArrayList<>();
        try {
            Page<Student> studentPage = studentService.page(new Page<>(current, size), new QueryWrapper<Student>().eq("class_Id", classService.getOne(new QueryWrapper<Class>().eq("class_Name", className)).getClassId()));
            this.size = studentPage.getTotal();//获取结果
            for (Student student : studentPage.getRecords()) {
                List<StudentWork> studentWorks = studentWorkService.list(new QueryWrapper<StudentWork>().eq("student_Id", student.getStudentId()).eq("work_Id", workId));
                for (StudentWork studentWork : studentWorks) {
                    studentWork.setStudentName(student.getStudentName());
                }
                student.setWorkSituation((studentWorks.size()>0?"已完成":"未完成"));//获取学生作业状态
                student.setStudentWorks(studentWorks);
                data.add(student);
                status = true;
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 教师端添加学生作业
     *
     * @param work 需提供：班级名，作业名称，教师编号；可提供：作业要求
     * @return Json
     */
    @PostMapping
    public Json addWorkByClassName(@RequestBody Work work) {
        boolean data = false;
        try {
            work.setClassId(classService.getOne(new QueryWrapper<Class>().eq("class_Name", work.getClassName())).getClassId());//通过班级名获取班级编号
            data = workService.save(work);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 教师端根据id删除(谨慎使用!)
     *
     * @param work 需提供：id
     * @return Json
     */
    @DeleteMapping
    public Json delWorkById(@RequestBody Work work) {
        boolean data = false;
        try {
            boolean data1 = studentWorkService.remove(new QueryWrapper<StudentWork>().eq("work_Id", work.getWorkId()));
            boolean data2 = workService.removeById(work.getWorkId());
            data = data1 | data2;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 教师端根据id修改
     *
     * @param work 需提供：作业id；可提供：教师id，作业名称，作业要求，班级名称
     * @return Json
     */
    @PutMapping
    public Json updateWorkById(@RequestBody Work work) {
        boolean data = false;
        try {
            work.setClassId(classService.getOne(new QueryWrapper<Class>().eq("class_Name", work.getClassName())).getClassId());//获取班级编号
            data = workService.updateById(work);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 教师端批改学生作业
     *
     * @param studentWork 需提供：id，分数
     * @return Json
     */
    @PatchMapping
    public Json updateSource(@RequestBody StudentWork studentWork) {
        boolean data = false;
        try {
            data = studentWorkService.updateById(studentWork);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }
}
