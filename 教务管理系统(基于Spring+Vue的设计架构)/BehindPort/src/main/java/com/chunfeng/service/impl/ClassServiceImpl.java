package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.entity.Class;
import com.chunfeng.dao.mapper.ClassMapper;
import com.chunfeng.service.inter.ClassService;
import org.springframework.stereotype.Service;

/**
 * 班级业务层实现类
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
}
