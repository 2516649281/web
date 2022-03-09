package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.UserMapper;
import com.chunfeng.entity.Json;
import com.chunfeng.entity.User;
import com.chunfeng.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层
 */
@Service
public class UserImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 数据
     */
    private Object data;

    /**
     * 运行状态
     */
    private Boolean status;

    /**
     * 查询数据量
     */
    private Long size;


    /**
     * 查询所有用户
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @Override
    public Json selectAll(int current, int size) {
        try {
            Page<User> userPage = userMapper.selectPage(new Page<>(current, size), null);
            this.size = userPage.getTotal();
            data = userPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size, null);
    }

    /**
     * 根据账号名查询账号
     *
     * @param current  页码
     * @param size     每页显示数
     * @param userName 用户名
     * @return Json
     */
    @Override
    public Json selectAllByName(int current, int size, String userName) {
        try {
            Page<User> userPage = userMapper.selectPage(new Page<>(current, size), new QueryWrapper<User>().like("user_Name", userName));
            this.size = userPage.getTotal();
            data = userPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size, null);
    }

    /**
     * 注册用户
     *
     * @param user 需提供:用户名,用户密码
     * @return Json
     */
    @Override
    public Json insertUser(User user) {
        try {
            data = userMapper.insert(user);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

    /**
     * 修改用户
     *
     * @param user 需提供:用户编号,可提供:用户名,用户密码
     * @return Json
     */
    @Override
    public Json updateUserById(User user) {
        try {
            data = userMapper.updateById(user);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

    /**
     * 冻结用户
     *
     * @param user 需提供:用户编号
     * @return Json
     */
    @Override
    public Json deleteUserById(User user) {
        try {
            data = userMapper.deleteById(user.getId());
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

    /**
     * 解冻账号
     *
     * @param user 需提供:账号编号
     * @return int
     */
    @Override
    public Json UnfreezeUser(User user) {
        try {
            data = userMapper.UnfreezeUser(user);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

    /**
     * 查询已冻结账号
     *
     * @param current 页码
     * @param size    每页显示数
     * @return list
     */
    @Override
    public Json selectAllByDeleted(int current, int size) {
        try {
            Page<User> userIPage = userMapper.selectAllByDeleted(new Page<>(current, size));
            this.size = userIPage.getTotal();
            data = userIPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size, null);
    }

    /**
     * 登录
     *
     * @param user 需提供:账号,密码
     * @return Json
     */
    @Override
    public Json login(User user) {
        try {
            data = userMapper.selectList(new QueryWrapper<User>().eq("user_Name", user.getUserName()).eq("user_Password", user.getUserPassword()));
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

}
