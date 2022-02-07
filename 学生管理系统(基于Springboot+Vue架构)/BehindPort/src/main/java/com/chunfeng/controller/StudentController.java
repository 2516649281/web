package com.chunfeng.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chunfeng.dao.pojo.Class;
import com.chunfeng.dao.pojo.Json;
import com.chunfeng.dao.pojo.Student;
import com.chunfeng.service.inter.ClassService;
import com.chunfeng.service.inter.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生控制层
 */
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    /**
     * 分页查询学生
     *
     * @param current 当前页数
     * @param size    每页数据数
     * @return Json
     */
    @GetMapping("/{current}/{size}")
    public Json selectStudentPage(@PathVariable int current, @PathVariable int size) {
        boolean status = true;
        ArrayList<Student> data = new ArrayList<>();
        Page<Student> studentPage = studentService.page(new Page<>(current, size));
        try {
            for (Student student : studentPage.getRecords()) {
                student.setClassName(classService.getById(student.getClassId()).getClassName());
                //先根据班级id查询班级名称，再将班级名称装载Student对象中
                student.setSize(studentPage.getTotal());//返回总数
                data.add(student);
            }
        } catch (Exception e) {
            //报错时更改状态码
            e.printStackTrace();
            status = false;
        }
        return new Json(status, data);
    }

    /**
     * 添加学生
     *
     * @param student 学生信息,无需填入班级id
     * @return Json
     */
    @PostMapping
    public Json addStudent(@RequestBody Student student) {
        boolean status = true;
        boolean data = false;
        try {
            student.setClassId(classService.getOne(new QueryWrapper<Class>().select("Id").eq("Class_Name", student.getClassName())).getId());
            //获取班级id，班级id不参与显示
            data = studentService.save(student);
        } catch (Exception e) {
            //报错时更改状态码
            e.printStackTrace();
            status = false;
        }
        return new Json(status, data);
    }

    /**
     * 删除学生
     *
     * @param studentId 学生id
     * @return Json
     */
    @DeleteMapping("/{studentId}")
    public Json delStudentById(@PathVariable int studentId) {
        boolean status = true;
        boolean data = false;
        try {
            data = studentService.removeById(studentId);
        } catch (Exception e) {
            //报错时更改状态码
            e.printStackTrace();
            status = false;
        }
        return new Json(status, data);
    }

    /**
     * 修改学生
     *
     * @param student 学生信息,需要传入学生id，无需传入班级id
     * @return Json
     */
    @PutMapping
    public Json updateStudentById(@RequestBody Student student) {
        boolean status = true;
        boolean data = false;
        try {
            student.setClassId(classService.getOne(new QueryWrapper<Class>().select("Id").eq("Class_Name", student.getClassName())).getId());
            //获取班级id，班级id不参与显示
            data = studentService.updateById(student);
        } catch (Exception e) {
            //报错时更改状态码
            e.printStackTrace();
            status = false;
        }
        return new Json(status, data);
    }


    /**
     * 根据班级分页查询学生
     *
     * @param current   页码
     * @param size      每页显示数
     * @param ClassName 班级名称
     */
    @GetMapping("/{current}/{size}/{ClassName}")
    public Json SelectStudentByClassName(@PathVariable int current, @PathVariable int size, @PathVariable String ClassName) {
        boolean status = true;
        List<Student> data = null;
        Page<Student> studentPage = new Page<>(current, size);
        try {
            data =studentService.page(studentPage, new QueryWrapper<Student>().eq("Class_Id", classService.getOne(new QueryWrapper<Class>().select("Id").eq("Class_Name", ClassName)).getId())).getRecords();
            //在班级表中查找班级id,用学生表中的班级id值查找学生
            for (Student student : data) {
                student.setSize(studentPage.getTotal());//返回总数
                student.setClassName(ClassName);
            }
        } catch (Exception e) {
            //报错时更改状态码
            e.printStackTrace();
            status = false;
        }
        return new Json(status, data);
    }

}
