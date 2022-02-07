package com;

import org.junit.Test;
import web.CheckCodeUtil;

import java.io.File;
import java.io.IOException;

/**
 * 查询测试类
 */
public class TestDemo {
    @Test
    public void select() {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = null;
//        SqlSession sqlSession = null;
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//            sqlSession = sqlSessionFactory.openSession();
//            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//            ArrayList<Student> students = mapper.SelectAll();
//            for (Student i : students) {
//                System.out.println(i);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (sqlSession != null) {
//                sqlSession.close();
//            }
//        }
//
//    }
    }

    @Test
    public void CheckTest() {
        File file = new File("D://a.jpg");
        try {
            String s = CheckCodeUtil.outputVerifyImage(100, 50, file, 4);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
