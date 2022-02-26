package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.entity.Teacher;
import com.chunfeng.dao.mapper.TeacherMapper;
import com.chunfeng.service.inter.TeacherService;
import org.springframework.stereotype.Service;

/**
 * 教师业务层实现类
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}
