package dao.Mapper;

import dao.pojo.LoginUser;

/**
 * 监控用户行为
 */
public interface UserLoginMapper {
    /**
     * 添加用户登录记录
     *
     * @param loginUser 登录用户信息
     */
    void AddUserLogin(LoginUser loginUser);
}
