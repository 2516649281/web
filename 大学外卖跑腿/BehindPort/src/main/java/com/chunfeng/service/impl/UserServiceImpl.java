package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.entity.Account;
import com.chunfeng.dao.entity.Json;
import com.chunfeng.dao.entity.User;
import com.chunfeng.dao.mapper.AccountMapper;
import com.chunfeng.dao.mapper.UserMapper;
import com.chunfeng.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 用户数据层
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 账号数据层
     */
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 运行状态
     */
    private Boolean status;

    /**
     * 数据
     */
    private Object data;

    /**
     * 查询总数
     */
    private Long pageSize;

    /**
     * 查询所有用户
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @Override
    public Json selectAllUser(int current, int size) {
        try {
            Page<User> userPage = userMapper.selectPage(new Page<>(current, size), null);
            data = userPage.getRecords();
            pageSize = userPage.getTotal();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, pageSize);
    }

    /**
     * 按照职务查询用户
     *
     * @param current 页码
     * @param size    每页显示数
     * @param duty    职务
     * @return Json
     */
    @Override
    public Json selectAllUserByDuty(int current, int size, String duty) {
        try {
            Page<User> userPage = userMapper.selectPage(new Page<>(current, size), new QueryWrapper<User>().eq("user_duty", duty));
            data = userPage.getRecords();
            pageSize = userPage.getTotal();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, pageSize);
    }

    /**
     * 添加用户信息
     *
     * @param user 需提供:姓名,年龄,性别,住址,电话号码,用户编号
     * @return Json
     */
    @Override
    public Json insertUser(User user) {
        try {
            data = userMapper.insert(user) > 0;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 删除用户信息
     *
     * @param user 用户编号
     * @return Json
     */
    @Override
    public Json deleteUser(User user) {
        try {
            Account account = new Account();
            account.setAccountId(user.getAccountId());
            if (accountMapper.selectAccountWrapper(new Page<>(1, 10), new QueryWrapper<Account>().eq("account_id", user.getUserId())).getRecords().size() > 0) {//判断用户是否存在
                data = accountMapper.deleteByAccountId(account) > 0;//删除账号
            }
            data = userMapper.deleteById(user.getUserId()) > 0;//删除用户
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 修改用户信息
     *
     * @param user 需提供:用户编号,可提供:姓名,年龄,性别,住址,电话号码
     * @return Json
     */
    @Override
    public Json updateUser(User user) {
        try {
            data = userMapper.updateById(user) > 0;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }
}