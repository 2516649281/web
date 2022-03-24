package com.chunfeng.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chunfeng.dao.entity.Account;
import com.chunfeng.dao.entity.Json;

/**
 * 账号业务
 */
public interface AccountService extends IService<Account> {

    /**
     * 查询所有账号(包含已注销用户)
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    Json selectAllAccount(int current, int size);

    /**
     * 指定查询用户(包含已注销的用户)
     *
     * @param current     页码
     * @param size        每页显示数
     * @param accountName 账号
     * @return Json
     */
    Json selectAccountWrapper(int current, int size, String accountName);

    /**
     * 添加账号
     *
     * @param account 需提供:账号,密码
     * @return Json
     */
    Json insertAccount(Account account);

    /**
     * 修改账号
     *
     * @param account 需提供:账号编号,可提供:账号,密码
     * @return Json
     */
    Json updateAccountById(Account account);

    /**
     * 冻结账号
     *
     * @param account 需提供:账号编号
     * @return Json
     */
    Json freezeAccountById(Account account);

    /**
     * 解冻账号
     *
     * @param account 需提供:账号编号
     * @return Json
     */
    Json unfreezeAccountById(Account account);

    /**
     * 永久删除账号(注销)
     *
     * @param account 需提供:用户编号
     * @return Json
     */
    Json deleteByAccountId(Account account);

    /**
     * 登录
     *
     * @param account 需提供:账号,密码
     * @return Json
     */
    Json login(Account account);

}
