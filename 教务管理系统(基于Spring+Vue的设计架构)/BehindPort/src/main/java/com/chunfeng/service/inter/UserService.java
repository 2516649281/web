package com.chunfeng.service.inter;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chunfeng.dao.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户业务层接口
 */
public interface UserService extends IService<User> {
    /**
     * 查询所有用户
     *
     * @param page 分页
     * @return user
     */
    IPage<User> selectAllUserPage(IPage<User> page);

    /**
     * 分页模糊查询用户
     *
     * @param page         分页
     * @param queryWrapper 条件
     * @return Json
     */
    IPage<User> selectUserWrapper(IPage<User> page, Wrapper<User> queryWrapper);

    /**
     * 解冻账号
     *
     * @param userId 用户编号
     * @return Boolean
     */
    Boolean UnfreezeUser(Long userId);

    /**
     * 删除用户信息
     *
     * @param userId 用户编号
     * @return Boolean
     */
    Boolean DeleteUser(Long userId);
}
