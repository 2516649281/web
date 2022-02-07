package dao.pojo;

import dao.Mapper.UserLoginMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 用户监控执行类
 */
public class UserMon {
    /**
     * 核心文件
     */
    public static String resource = "mybatis-config.xml";
    /**
     * 字节流
     */
    public static InputStream inputStream = null;
    /**
     * sql执行对象
     */
    public static SqlSession sqlSession = null;
    /**
     * 用户登录对象
     */
    public static LoginUser loginUser;

    /**
     * 添加用户登录信息
     *
     * @param username  用户名
     * @param LoginTime 登录时间
     * @param EndTime   退出时间
     */
    public static void AddLogin(String username, String LoginTime, String EndTime) {
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserLoginMapper mapper = sqlSession.getMapper(UserLoginMapper.class);
            loginUser = new LoginUser(username, LoginTime, EndTime);
            mapper.AddUserLogin(loginUser);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
