package com.chunfeng.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chunfeng.config.Json;
import com.chunfeng.dao.entity.Class;
import com.chunfeng.dao.entity.Student;
import com.chunfeng.dao.entity.Teacher;
import com.chunfeng.dao.entity.User;
import com.chunfeng.service.inter.ClassService;
import com.chunfeng.service.inter.StudentService;
import com.chunfeng.service.inter.TeacherService;
import com.chunfeng.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 状态码
     */
    private boolean status;

    /**
     * 响应信息
     */
    private User data;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClassService classService;

    /**
     * 登录验证
     *
     * @param user 用户名和密码
     * @return Boolean
     */
    @PostMapping(params = "login")
    public Json Login(@RequestBody User user) {
        try {
            data = userService.getOne(new QueryWrapper<User>().eq("user_Name", user.getUserName()).eq("user_password", user.getUserPassword()));//获取提交信息
            data.setAdmin(data.getUserAdmin() == 0 ? "非管理员" : "管理员");//获取权限
            data.setIdentity(data.getUserIdentity() == 0 ? "学生" : "教师");//获取身份
            try {
                Teacher teacher = teacherService.getOne(new QueryWrapper<Teacher>().eq("user_Id", data.getUserId()));
                Student student = studentService.getOne(new QueryWrapper<Student>().eq("user_Id", data.getUserId()));
                data.setUserRealName(data.getUserIdentity() == 0 ? (student.getStudentName()) : (teacher.getTeacherName()));//获取姓名
                data.setUserIdentityId(data.getUserIdentity() == 0 ? (student.getStudentId()) : (teacher.getTeacherId()));//获取编号
                Class aClass = classService.getOne(new QueryWrapper<Class>().eq("class_Id", student.getClassId()));//获取班级信息
                data.setClassName(aClass.getClassName());
            } catch (NullPointerException s) {//如果查询捕获到的是空指针异常，则断定真实姓名为空，状态码放行，因为部分账号存在此现象
                status = true;
                s.printStackTrace();
            }
            status = true;
            if (data == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 注册并绑定用户身份信息
     *
     * @param user 需提供：用户名，密码，真实姓名(userRealName)，用户身份码(0为学生，1为教师)，真实班级(className)或地址(address)
     * @return Boolean
     */
    @PostMapping(params = "register")
    public Json Register(@RequestBody User user) {
        try {
            data = userService.getOne(new QueryWrapper<User>().eq("user_Name", user.getUserName()));
            if (data == null) {//判断用户名是否已经存在
                if (userService.save(user)) {//学生
                    User users = userService.getOne(new QueryWrapper<User>().eq("user_Name", user.getUserName()));//获取新用户信息
                    if (users.getUserIdentity() == 0) {//判断用户身份
                        Student student = studentService.getOne(new QueryWrapper<Student>().eq("student_Name", user.getUserRealName()).eq("class_Id", classService.getOne(new QueryWrapper<Class>().eq("class_Name", user.getClassName())).getClassId()));//查询学生信息
                        student.setUserId(users.getUserId());//获取用户编号
                        studentService.update(student, new QueryWrapper<Student>().eq("student_Id", student.getStudentId()));//将用户编号导入学生信息库
                    } else {
                        Teacher teacher = teacherService.getOne(new QueryWrapper<Teacher>().eq("teacher_Name", user.getUserRealName()).eq("teacher_Address", user.getAddress()));//查询教师信息
                        teacher.setUserId(users.getUserId());//获取用户编号
                        teacherService.update(teacher, new QueryWrapper<Teacher>().eq("teacher_Id", teacher.getTeacherId()));//将用户编号导入教师信息库
                    }
                    data = user;
                }
            }
        } catch (Exception e) {//报错数据不合法，直接不处理，设置状态码为false
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 修改用户信息
     *
     * @param user 需提供：原始账号和密码，可提供：新账号或密码
     * @return Json
     */
    @PutMapping
    public Json updateUserPassWord(@RequestBody User user) {
        User newUser = new User();
        try {
            if (userService.getOne(new QueryWrapper<User>().eq("user_Name", user.getUserName()).eq("user_Password", user.getUserPassword())) != null) {
                if (user.getNewUserName() != null) {
                    newUser.setUserName(user.getNewUserName());//更新
                }
                if (user.getNewUserPassWord() != null) {
                    newUser.setUserPassword(user.getNewUserPassWord());
                }
                status = userService.update(newUser, new QueryWrapper<User>().eq("user_Name", user.getUserName()));
                data = newUser;
                status = true;
            } else {
                status = false;
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 校验学生信息
     *
     * @param studentName 学生姓名
     * @param className   班级名称
     * @return Json
     */
    @GetMapping(value = "/{studentName}/{className}", params = "userStudent")
    public Json selectAllStudent(@PathVariable String studentName, @PathVariable String className) {
        Student data = null;
        try {
            Class aClass = classService.getOne(new QueryWrapper<Class>().eq("class_Name", className));
            data = studentService.getOne(new QueryWrapper<Student>().eq("student_Name", studentName).eq("class_Id", aClass.getClassId()));//查询学生班级
            data.setClassName(aClass.getClassName());
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 校验教师信息
     *
     * @param teacherName 教师姓名
     * @param address     地址
     * @return Json
     */
    @GetMapping(value = "/{teacherName}/{address}", params = "userTeacher")
    public Json selectAllTeacher(@PathVariable String teacherName, @PathVariable String address) {
        Teacher data = null;
        try {
            data = teacherService.getOne(new QueryWrapper<Teacher>().eq("teacher_Name", teacherName).eq("teacher_Address", address));
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }
}
