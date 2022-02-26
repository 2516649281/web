package com.chunfeng.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chunfeng.dao.entity.User;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


/**
 * 用户mapper
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有用户
     *
     * @param page 分页
     * @return user
     */
    @Select("SELECT * FROM user")
    IPage<User> selectAllUserPage(IPage<User> page);


    /**
     * 分页模糊查询用户
     *
     * @param page         分页
     * @param queryWrapper 条件
     * @return Json
     */
    @Select("select * from user ${ew.customSqlSegment}")
    IPage<User> selectUserWrapper(IPage<User> page, @Param("ew") Wrapper<User> queryWrapper);

    /**
     * 解冻账号
     *
     * @param userId 用户名
     * @return int
     */
    @Update("UPDATE user SET User_Deleted=0 WHERE User_Id = #{userId}")
    int UnfreezeUser(Long userId);

    /**
     * 删除用户信息
     *
     * @param userId 用户编号
     * @return int
     */
    @Delete("DELETE FROM user WHERE User_Id=#{userId}")
    int DeleteUser(Long userId);
}
