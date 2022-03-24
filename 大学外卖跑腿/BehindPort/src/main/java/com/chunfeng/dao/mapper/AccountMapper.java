package com.chunfeng.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chunfeng.dao.entity.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 账号mapper
 */
@Repository
public interface AccountMapper extends BaseMapper<Account> {
    /**
     * 查询所有用户(包含已注销用户)
     *
     * @param page 分页
     * @return page
     */
    @Select("select * from account")
    Page<Account> selectAllAccount(Page<Account> page);

    /**
     * 条件查询用户(包含已注销的用户)
     *
     * @param page    分页
     * @param wrapper 条件构造
     * @return Account
     */
    @Select("select * from account ${ew.customSqlSegment}")
    Page<Account> selectAccountWrapper(Page<Account> page, @Param("ew") Wrapper<Account> wrapper);

    /**
     * 永久删除用户
     *
     * @param account 需提供:用户编号
     * @return 影响行数
     */
    @Delete("delete from account where account_id=#{accountId}")
    int deleteByAccountId(Account account);

    /**
     * 解冻账号
     *
     * @param account 需提供:账号编号
     * @return Json
     */
    @Update("update account set account_deleted = 0 where account_id=#{accountId};")
    int updateAccountDeleted(Account account);
}
