package dao.Mapper;

import dao.pojo.Student;

import java.util.ArrayList;

/**
 * 学生实体类
 */
public interface StudentMapper {
    /**
     * 查看所有学生
     *
     * @return ArrayList
     */
    ArrayList<Student> SelectAll();

    /**
     * 删除学生
     *
     * @param id
     * @return int
     */
    int Delete(int id);

    /**
     * 添加学生
     *
     * @param student
     */
    void Add(Student student);

    /**
     * 修改学生
     *
     * @param student
     */
    void Edit(Student student);
}
