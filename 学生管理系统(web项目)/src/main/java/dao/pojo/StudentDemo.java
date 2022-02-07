package dao.pojo;

import dao.Mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * 学生执行类
 */
public class StudentDemo {

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
     * 查询所有学生(主页)
     *
     * @return ArrayList
     */
    public static ArrayList<Student> SelectAllStudent() {
        ArrayList<Student> students = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            students = mapper.SelectAll();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return students;
    }

    /**
     * 添加学生
     *
     * @param id      编号
     * @param name    姓名
     * @param age     年龄
     * @param sex     性别
     * @param address 地址
     */
    public static void AddStudent(int id, String name, int age, String sex, String address) {
        Student student = new Student(id, name, age, sex, address);
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            mapper.Add(student);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 删除学生
     *
     * @param id 编号
     */
    public static void DeleteStudent(int id) {
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            mapper.Delete(id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 修改学生
     *
     * @param id      编号
     * @param name    姓名
     * @param age     年龄
     * @param sex     性别
     * @param address 地址
     */
    public static void EditStudent(int id, String name, int age, String sex, String address) {
        Student student = new Student(id, name, age, sex, address);
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            mapper.Edit(student);
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
