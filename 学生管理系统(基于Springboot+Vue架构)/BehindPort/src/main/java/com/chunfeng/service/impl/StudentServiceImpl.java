package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.mapper.StudentMapper;
import com.chunfeng.dao.pojo.Student;
import com.chunfeng.service.inter.StudentService;
import org.springframework.stereotype.Service;

/**
 * 学生业务层
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
