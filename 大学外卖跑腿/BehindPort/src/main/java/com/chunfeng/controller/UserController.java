package com.chunfeng.controller;

import com.chunfeng.dao.entity.Json;
import com.chunfeng.dao.entity.User;
import com.chunfeng.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口类
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用户业务层
     */
    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @GetMapping("/{current}/{size}")
    Json selectAllUser(@PathVariable int current, @PathVariable int size) {
        return userService.selectAllUser(current, size);
    }

    /**
     * 按照职务查询用户
     *
     * @param current 页码
     * @param size    每页显示数
     * @param duty    职务
     * @return Json
     */
    @GetMapping("/{current}/{size}/{duty}")
    Json selectAllUserByDuty(@PathVariable int current, @PathVariable int size, @PathVariable String duty) {
        return userService.selectAllUserByDuty(current, size, duty);
    }

    /**
     * 添加用户信息
     *
     * @param user 需提供:姓名,年龄,性别,住址,电话号码,用户编号
     * @return Json
     */
    @PostMapping
    Json insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    /**
     * 修改用户信息
     *
     * @param user 需提供:用户编号,可提供:姓名,年龄,性别,住址,电话号码,用户编号
     * @return Json
     */
    @PutMapping
    Json updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * 删除用户信息
     *
     * @param user 用户信息
     * @return Json
     */
    @DeleteMapping
    Json deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }
}
