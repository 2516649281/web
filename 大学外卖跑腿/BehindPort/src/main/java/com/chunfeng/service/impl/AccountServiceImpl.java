package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.entity.Account;
import com.chunfeng.dao.entity.Json;
import com.chunfeng.dao.entity.User;
import com.chunfeng.dao.mapper.AccountMapper;
import com.chunfeng.dao.mapper.UserMapper;
import com.chunfeng.service.inter.AccountService;
import com.chunfeng.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账号业务实现
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    /**
     * 账号数据层
     */
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 用户数据层
     */
    @Autowired
    private UserMapper userMapper;

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
     * 永久删除账号(注销)
     *
     * @param account 需提供:用户编号
     * @return 影响行数
     */
    @Override
    public Json deleteByAccountId(Account account) {
        try {
            data = accountMapper.deleteByAccountId(account) > 0;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 查询所有账号(包含已注销用户)
     *
     * @param current 页码
     * @param size    每页显示数
     * @return page
     */
    @Override
    public Json selectAllAccount(int current, int size) {
        try {
            Page<Account> accountPage = accountMapper.selectAllAccount(new Page<>(current, size));
            data = accountPage.getRecords();
            pageSize = accountPage.getTotal();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, pageSize);
    }

    /**
     * 条件查询用户(包含已注销的用户)
     *
     * @param current     页码
     * @param size        每页显示数
     * @param accountName 账号
     * @return Account
     */
    @Override
    public Json selectAccountWrapper(int current, int size, String accountName) {
        try {
            Page<Account> accountPage = accountMapper.selectAccountWrapper(new Page<>(current, size), new QueryWrapper<Account>().like("account_Name", accountName));
            data = accountPage.getRecords();
            pageSize = accountPage.getTotal();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, pageSize);
    }

    /**
     * 添加账号
     *
     * @param account 需提供:账号,密码
     * @return Json
     */
    @Override
    public Json insertAccount(Account account) {
        try {
            data = accountMapper.insert(account) > 0;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 修改账号
     *
     * @param account 需提供:账号编号,可提供:账号,密码
     * @return Json
     */
    @Override
    public Json updateAccountById(Account account) {
        try {
            data = accountMapper.updateById(account) > 0;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 冻结账号
     *
     * @param account 需提供:账号编号
     * @return Json
     */
    @Override
    public Json freezeAccountById(Account account) {
        try {
            data = accountMapper.deleteById(account.getAccountId()) > 0;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 解冻账号
     *
     * @param account 需提供:账号编号
     * @return Json
     */
    @Override
    public Json unfreezeAccountById(Account account) {
        try {
            data = accountMapper.updateAccountDeleted(account) > 0;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 登录
     *
     * @param account 需提供:账号,密码
     * @return Json
     */
    @Override
    public Json login(Account account) {
        try {
            Account accountObject = accountMapper.selectOne(new QueryWrapper<Account>().eq("account_Name", account.getAccountName()).eq("account_Password", account.getAccountPassword()));
            if (accountObject != null) {
                accountObject.setUser(userMapper.selectOne(new QueryWrapper<User>().eq("account_Id", accountObject.getAccountId())));//将用户真实信息传递给对象
                account.setToken(JwtToken.createToken(accountObject));//验证成功则返回token
            }
            data = account;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }
}
