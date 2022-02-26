package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.entity.User;
import com.chunfeng.dao.mapper.UserMapper;
import com.chunfeng.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有用户
     * @param page 分页
     * @return user
     */
    public IPage<User> selectAllUserPage(IPage<User> page) {
        return userMapper.selectAllUserPage(page);
    }

    /**
     * 分页模糊查询用户
     *
     * @param page         分页
     * @param queryWrapper 条件
     * @return Json
     */
    @Override
    public IPage<User> selectUserWrapper(IPage<User> page, Wrapper<User> queryWrapper) {
        return userMapper.selectUserWrapper(page, queryWrapper);
    }

    /**
     * 解冻账号
     *
     * @param userId 用户编号
     * @return Boolean
     */
    @Override
    public Boolean UnfreezeUser(Long userId) {
        return userMapper.UnfreezeUser(userId) > 0;
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户编号
     * @return Boolean
     */
    @Override
    public Boolean DeleteUser(Long userId) {
        return userMapper.DeleteUser(userId) > 0;
    }
}
