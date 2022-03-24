package com.chunfeng.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chunfeng.dao.entity.Json;
import com.chunfeng.dao.entity.User;

/**
 * 用户业务
 */
public interface UserService extends IService<User> {

    /**
     * 查询所有用户
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    Json selectAllUser(int current, int size);

    /**
     * 按照职务查询用户
     *
     * @param current 页码
     * @param size    每页显示数
     * @param duty    职务
     * @return Json
     */
    Json selectAllUserByDuty(int current, int size, String duty);

    /**
     * 添加用户信息
     *
     * @param user 需提供:姓名,年龄,性别,住址,电话号码,用户编号
     * @return Json
     */
    Json insertUser(User user);

    /**
     * 删除用户信息
     *
     * @param user 需提供:用户编号
     * @return Json
     */
    Json deleteUser(User user);

    /**
     * 修改用户信息
     *
     * @param user 需提供:用户编号,可提供:姓名,年龄,性别,住址,电话号码,用户编号
     * @return Json
     */
    Json updateUser(User user);
}
