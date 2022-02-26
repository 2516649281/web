package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.entity.Work;
import com.chunfeng.dao.mapper.WorkMapper;
import com.chunfeng.service.inter.WorkService;
import org.springframework.stereotype.Service;

/**
 * 作业业务层实现类
 */
@Service
public class WorkServerImpl extends ServiceImpl<WorkMapper, Work> implements WorkService {
}
