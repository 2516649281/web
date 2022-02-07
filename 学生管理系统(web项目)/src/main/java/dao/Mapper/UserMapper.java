package dao.Mapper;

import dao.pojo.User;

/**
 * 用户实体类
 */
public interface UserMapper {
    /**
     * 用户登录
     *
     * @return ArrayList
     */
    String SelectAll(User user);

    /**
     * 注册用户
     *
     * @param user
     */
    void AddUser(User user);
}
