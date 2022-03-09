package com.chunfeng.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chunfeng.entity.Json;
import com.chunfeng.entity.User;

/**
 * 用户service
 */
public interface UserService extends IService<User> {

    /**
     * 查询所有用户
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    Json selectAll(int current, int size);

    /**
     * 根据账号名查询账号
     *
     * @param current  页码
     * @param size     每页显示数
     * @param userName 用户名
     * @return Json
     */
    Json selectAllByName(int current, int size, String userName);

    /**
     * 注册用户
     *
     * @param user 需提供:用户名,用户密码
     * @return Json
     */
    Json insertUser(User user);

    /**
     * 修改用户
     *
     * @param user 需提供:用户编号,可提供:用户名,用户密码
     * @return Json
     */
    Json updateUserById(User user);

    /**
     * 冻结用户
     *
     * @param user 需提供:用户编号
     * @return Json
     */
    Json deleteUserById(User user);

    /**
     * 解冻账号
     *
     * @param user 需提供:账号编号
     * @return Json
     */
    Json UnfreezeUser(User user);

    /**
     * 查询已冻结账号
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    Json selectAllByDeleted(int current, int size);

    /**
     * 登录
     * @param user 需提供:账号,密码
     * @return Json
     */
    Json login(User user);
}
