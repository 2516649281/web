package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.entity.StudentWork;
import com.chunfeng.dao.mapper.StudentWorkMapper;
import com.chunfeng.service.inter.StudentWorkService;
import org.springframework.stereotype.Service;

/**
 * 学生-作业业务层实现类
 */
@Service
public class StudentWorkServiceImpl extends ServiceImpl<StudentWorkMapper, StudentWork> implements StudentWorkService {
}
