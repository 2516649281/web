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

/**
 * 学生端
 */
@RestController
@RequestMapping("/studentPost")
public class StudentController {
    /**
     * 状态码
     */
    private boolean status;

    /**
     * 查询结果总数
     */
    private Long size;

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
     * 根据学生编号查询作业信息
     *
     * @param current   页码
     * @param size      每页显示数
     * @param studentId 学生编号
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}/{studentId}", params = "student")
    public Json selectAllByStudentName(@PathVariable String studentId, @PathVariable int current, @PathVariable int size) {
        ArrayList<Student> data = new ArrayList<>();
        try {
            for (Student student : studentService.list(new QueryWrapper<Student>().eq("student_Id", studentId))) {
                student.setClassName(classService.getOne(new QueryWrapper<Class>().eq("class_Id", student.getClassId())).getClassName());//将班级名称插入集合
                Page<StudentWork> studentWorkPage = studentWorkService.page(new Page<>(current, size), new QueryWrapper<StudentWork>().eq("student_Id", student.getStudentId()));
                this.size = studentWorkPage.getTotal();//获取查询数目
                student.setStudentWorks(studentWorkPage.getRecords());//将作业填充
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
     * 根据班级查询所有作业信息
     *
     * @param current   页码
     * @param size      每页条数
     * @param className 班级名称
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}/{className}", params = "class")
    public Json selectAllByClassName(@PathVariable int current, @PathVariable int size, @PathVariable String className) {
        ArrayList<Work> data = new ArrayList<>();
        try {
            Page<Work> workPage = workService.page(new Page<>(current, size), new QueryWrapper<Work>().eq("class_Id", classService.getOne(new QueryWrapper<Class>().eq("class_Name", className)).getClassId()));
            this.size = workPage.getTotal();//获取查询数目
            for (Work work : workPage.getRecords()) {
                work.setTeacherName(teacherService.getOne(new QueryWrapper<Teacher>().eq("teacher_Id", work.getTeacherId())).getTeacherName());
                data.add(work);
                status = true;
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 根据id值提交学生作业
     *
     * @param studentWork 需提供：学生编号，学生作业，作业编号
     * @return Json
     */
    @PostMapping
    public Json addWorkById(@RequestBody StudentWork studentWork) {
        boolean data = false;
        try {
            data = studentWorkService.save(studentWork);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 根据id值修改学生作业
     *
     * @param studentWork 需提供：学生作业编号，学生作业；可提供：作业编号
     * @return Json
     */
    @PutMapping
    public Json updateWorkById(@RequestBody StudentWork studentWork) {
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

    /**
     * 根据id值删除学生作业
     *
     * @param studentWork 需提供：学生作业编号
     * @return Json
     */
    @DeleteMapping
    public Json delWorkById(@RequestBody StudentWork studentWork) {
        boolean data = false;
        try {
            data = studentWorkService.removeById(studentWork.getStudentWorkId());
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }
}
