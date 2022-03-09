package com.chunfeng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chunfeng.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 用户mapper
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 解冻账号
     *
     * @param user 需提供:账号编号
     * @return int
     */
    @Update("update user set deleted = 0 where id=#{id};")
    int UnfreezeUser(User user);

    /**
     * 查询已冻结账号
     *
     * @return list
     */
    @Select("select * from user where deleted=1")
    Page<User> selectAllByDeleted(IPage<User> page);
}
