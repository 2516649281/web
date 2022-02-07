package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.mapper.ClassMapper;
import com.chunfeng.dao.pojo.Class;
import com.chunfeng.service.inter.ClassService;
import org.springframework.stereotype.Service;

/**
 * 班级业务层
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
}
