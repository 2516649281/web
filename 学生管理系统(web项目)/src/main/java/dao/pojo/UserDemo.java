package dao.pojo;

import dao.Mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 用户执行类
 */
public class UserDemo {
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
     * 用户对象
     */
    public static User user;

    /**
     * 验证用户
     *
     * @return password 密码
     */
    public static String SelectUser(String username, String password) {
        String password_db = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = new User(username, password);
            password_db = mapper.SelectAll(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return password_db;
    }

    /**
     * 添加用户
     *
     * @param username 用户名
     * @param password 密码
     */
    public static void AddUser(String username, String password) {
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = new User(username, password);
            mapper.AddUser(user);
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
