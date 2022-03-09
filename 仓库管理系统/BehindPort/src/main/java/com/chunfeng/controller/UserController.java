package com.chunfeng.controller;

import com.chunfeng.entity.Json;
import com.chunfeng.entity.User;
import com.chunfeng.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

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
    public Json selectAll(@PathVariable int current, @PathVariable int size) {
        return userService.selectAll(current, size);
    }

    /**
     * 根据账号名查询账号
     *
     * @param current  页码
     * @param size     每页显示数
     * @param userName 用户名
     * @return Json
     */
    @GetMapping("/{current}/{size}/{userName}")
    public Json selectAllByName(@PathVariable int current, @PathVariable int size, @PathVariable String userName) {
        return userService.selectAllByName(current, size, userName);
    }

    /**
     * 注册用户
     *
     * @param user 需提供:用户名,用户密码
     * @return Json
     */
    @PostMapping
    public Json insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    /**
     * 修改用户
     *
     * @param user 需提供:用户编号,可提供:用户名,用户密码
     * @return Json
     */
    @PutMapping
    public Json updateUserById(@RequestBody User user) {
        return userService.updateUserById(user);
    }

    /**
     * 冻结用户
     *
     * @param user 需提供:用户编号
     * @return Json
     */
    @DeleteMapping
    public Json deleteUserById(@RequestBody User user) {
        return userService.deleteUserById(user);
    }

    /**
     * 解冻账号
     *
     * @param user 需提供:账号编号
     * @return int
     */
    @PutMapping(params = "unfree")
    public Json UnfreezeUser(User user) {
        return userService.UnfreezeUser(user);
    }

    /**
     * 查询已冻结账号
     *
     * @param current 页码
     * @param size    每页显示数
     * @return list
     */
    @GetMapping(value = "/{current}/{size}", params = "delete")
    public Json selectAllByDeleted(@PathVariable int current, @PathVariable int size) {
        return userService.selectAllByDeleted(current, size);
    }

    /**
     * 登录
     *
     * @param user 需提供:账号,密码
     * @return Json
     */
    @PostMapping(params = "login")
    public Json login(@RequestBody User user) {
        return userService.login(user);
    }
}
