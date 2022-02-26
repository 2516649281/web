package com.chunfeng.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * 管理端
 */
@RestController
@RequestMapping("/adminPost")
public class AdminController {
    /**
     * 状态码
     */
    private boolean status;

    /**
     * 查询结果数目
     */
    private Long size;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassService classService;

    @Autowired
    private WorkService workService;

    @Autowired
    private StudentWorkService studentWorkService;

    /**
     * 查询所有学生信息
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}", params = "student")
    public Json selectAllStudent(@PathVariable int current, @PathVariable int size) {
        List<Student> data = new ArrayList<>();
        try {
            Page<Student> studentPage = studentService.page(new Page<>(current, size));
            this.size = studentPage.getTotal();
            for (Student student : studentPage.getRecords()) {
                student.setClassName(classService.getOne(new QueryWrapper<Class>().eq("class_Id", student.getClassId())).getClassName());//添加班级名称
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
     * 通过班级名查询
     *
     * @param current   页码
     * @param size      每页显示数
     * @param className 班级名
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}/{className}", params = "student")
    public Json selectAllStudentByClassName(@PathVariable int current, @PathVariable int size, @PathVariable String className) {
        List<Student> data = new ArrayList<>();
        try {
            Page<Student> studentPage = studentService.page(new Page<>(current, size), new QueryWrapper<Student>().eq("class_Id", classService.getOne(new QueryWrapper<Class>().eq("class_Name", className)).getClassId()));
            this.size = studentPage.getTotal();
            for (Student student : studentPage.getRecords()) {
                student.setClassName(className);//添加班级名称
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
     * 添加学生
     *
     * @param student 需提供：学生姓名，学生年龄，学生性别，学生地址，班级名称
     * @return Json
     */
    @PostMapping(params = "student")
    public Json addStudent(@RequestBody Student student) {
        boolean data = false;
        try {
            student.setClassId(classService.getOne(new QueryWrapper<Class>().eq("class_Name", student.getClassName())).getClassId());//获取班级编号并添加至学生表
            data = studentService.save(student);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 修改学生信息
     *
     * @param student 需提供：学生编号；可提供：学生姓名，学生年龄，学生性别，学生地址，班级名称
     * @return Json
     */
    @PutMapping(params = "student")
    public Json updateStudent(@RequestBody Student student) {
        boolean data = false;
        try {
            student.setClassId(classService.getOne(new QueryWrapper<Class>().eq("class_Name", student.getClassName())).getClassId());//获取班级编号并添加至学生表
            data = studentService.updateById(student);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 删除学生信息
     *
     * @param student 需提供：学生编号
     * @return Json
     */
    @DeleteMapping(params = "student")
    public Json deleteStudentById(@RequestBody Student student) {
        boolean data = false;
        try {
            Student students = studentService.getOne(new QueryWrapper<Student>().eq("student_Id", student.getStudentId()));//获取学生信息
            List<StudentWork> studentWorks = studentWorkService.list(new QueryWrapper<StudentWork>().eq("student_Id", student.getStudentId()));//获取学生作业信息
            if (studentWorks.size() > 0) {//判断学生是否提交过作业
                studentWorkService.remove(new QueryWrapper<StudentWork>().eq("student_Id", student.getStudentId()));//删除学生已提交的作业信息
            }
            data = studentService.remove(new QueryWrapper<Student>().eq("student_Id", student.getStudentId()));
            if (students.getUserId() != null) {//判断账号是否存在
                userService.DeleteUser(students.getUserId());//删除学生账号信息
            }
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 查询所有教师信息
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}", params = "teacher")
    public Json selectAllTeacher(@PathVariable int current, @PathVariable int size) {
        List<Teacher> data = new ArrayList<>();
        try {
            Page<Teacher> teacherPage = teacherService.page(new Page<>(current, size));
            this.size = teacherPage.getTotal();
            data = teacherPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 根据地址模糊查询教师信息
     *
     * @param current 页码
     * @param size    每页显示数
     * @param address 教师地址
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}/{address}", params = "teacher")
    public Json selectAllTeacherByAddress(@PathVariable int current, @PathVariable int size, @PathVariable String address) {
        List<Teacher> data = new ArrayList<>();
        try {
            Page<Teacher> teacherPage = teacherService.page(new Page<>(current, size), new QueryWrapper<Teacher>().like("teacher_Address", address));
            this.size = teacherPage.getTotal();
            data = teacherPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 添加教师信息
     *
     * @param teacher 需提供：教师姓名，教师年龄，教师性别，教师地址
     * @return Json
     */
    @PostMapping(params = "teacher")
    public Json addTeacher(@RequestBody Teacher teacher) {
        boolean data = false;
        try {
            data = teacherService.save(teacher);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 修改教师信息
     *
     * @param teacher 需提供：教师id；可提供：教师姓名，教师年龄，教师性别，教师地址
     * @return Json
     */
    @PutMapping(params = "teacher")
    public Json updateTeacherById(@RequestBody Teacher teacher) {
        boolean data = false;
        try {
            data = teacherService.updateById(teacher);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 删除教师信息
     *
     * @param teacher 需提供：教师Id
     * @return Json
     */
    @DeleteMapping(params = "teacher")
    public Json deleteTeacherById(@RequestBody Teacher teacher) {
        boolean data = false;
        try {
            List<Work> works = workService.list(new QueryWrapper<Work>().eq("teacher_Id", teacher.getTeacherId()));//获取作业信息
            if (works.size() > 0) {
                for (Work work : works) {
                    studentWorkService.remove(new QueryWrapper<StudentWork>().eq("work_Id", work.getWorkId()));//删除所有已提交的作业
                    workService.remove(new QueryWrapper<Work>().eq("work_Id", work.getWorkId()));//删除已布置的作业信息
                }
            }
            Teacher teachers = teacherService.getOne(new QueryWrapper<Teacher>().eq("teacher_Id", teacher.getTeacherId()));//获取账号信息
            data = teacherService.removeById(teacher.getTeacherId());//删除教师
            if (teachers.getUserId() != null) {
                data=userService.DeleteUser(teachers.getUserId());//删除该教师所有账号信息
            }
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 查询所有正常用户信息
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}", params = "User")
    public Json selectAllOkUser(@PathVariable int current, @PathVariable int size) {
        List<User> data = new ArrayList<>();
        try {
            Page<User> userPage = userService.page(new Page<>(current, size));
            this.size = userPage.getTotal();
            data = userPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 查询所有用户信息
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}", params = "allUser")
    public Json selectAllUser(@PathVariable int current, @PathVariable int size) {
        List<User> data = new ArrayList<>();
        try {
            IPage<User> userPage = userService.selectAllUserPage(new Page<>(current, size));
            data = userPage.getRecords();
            this.size = userPage.getTotal();//获取用户数量
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 模糊查询所有用户
     *
     * @param current  页码
     * @param size     每页显示数
     * @param userName 用户名
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}/{userName}", params = "allUser")
    public Json selectAllUserLikeUserName(@PathVariable int current, @PathVariable int size, @PathVariable String userName) {
        List<User> data = new ArrayList<>();
        try {
            IPage<User> user = userService.selectUserWrapper(new Page<>(current, size), new QueryWrapper<User>().like("user_Name", userName));
            data = user.getRecords();
            this.size = user.getTotal();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 根据用户名模糊查询正常用户
     *
     * @param current  页码
     * @param size     每页显示数
     * @param userName 用户名
     * @return Json
     */
    @GetMapping(value = "{current}/{size}/{userName}", params = "user")
    public Json selectAllUserByUserName(@PathVariable int current, @PathVariable int size, @PathVariable String userName) {
        List<User> data = new ArrayList<>();
        try {
            Page<User> userPage = userService.page(new Page<>(current, size), new QueryWrapper<User>().like("user_Name", userName));
            this.size = userPage.getTotal();
            data = userPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 根据用户名冻结指定账号
     *
     * @param user 需提供：用户编号
     * @return Json
     */
    @DeleteMapping(params = "user")
    public Json delUserByUserName(@RequestBody User user) {
        boolean data = false;
        try {
            user.setUserPassword("000000");
            data = userService.updateById(user);//重置密码
            if (data) {
                data = userService.remove(new QueryWrapper<User>().eq("user_Id", user.getUserId()));
            }
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 根据用户名解冻账号
     *
     * @param user 需提供：用户名
     * @return Json
     */
    @PutMapping(params = "user")
    public Json UnfreezeUser(@RequestBody User user) {
        boolean data = false;
        try {
            data = userService.UnfreezeUser(user.getUserId());
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 授予与撤销管理员权限
     *
     * @param user 需提供：用户编号，管理员标识(0为非管理，1为管理)
     * @return Json
     */
    @PutMapping(params = "admin")
    public Json addAdminUser(@RequestBody User user) {
        boolean data = false;
        try {
            data = userService.updateById(user);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 查询所有班级
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}", params = "class")
    public Json selectAllClass(@PathVariable int current, @PathVariable int size) {
        List<Class> data = new ArrayList<>();
        try {
            Page<Class> classPage = classService.page(new Page<>(current, size));
            this.size = classPage.getTotal();
            data = classPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 根据班级名模糊查询所有班级
     *
     * @param current   页码
     * @param size      每页显示数
     * @param className 班级名
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}/{className}", params = "class")
    public Json selectAllClassByClassName(@PathVariable int current, @PathVariable int size, @PathVariable String className) {
        List<Class> data = new ArrayList<>();
        try {
            Page<Class> classPage = classService.page(new Page<>(current, size), new QueryWrapper<Class>().like("class_Name", className));
            this.size = classPage.getTotal();
            data = classPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size);
    }

    /**
     * 添加班级信息
     *
     * @param aClass 需提供：班级名称
     * @return Json
     */
    @PostMapping(params = "class")
    public Json addClass(@RequestBody Class aClass) {
        boolean data = false;
        try {
            data = classService.save(aClass);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 根据id修改班级信息
     *
     * @param aClass 需提供：id，新班级名
     * @return Json
     */
    @PutMapping(params = "class")
    public Json updateClassById(@RequestBody Class aClass) {
        boolean data = false;
        try {
            data = classService.updateById(aClass);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 根据id删除班级
     *
     * @param aClass 需提供：id
     * @return Json
     */
    @DeleteMapping(params = "class")
    public Json deleteClassById(@RequestBody Class aClass) {
        boolean data = false;
        try {
            data = classService.removeById(aClass.getClassId());
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }
}
