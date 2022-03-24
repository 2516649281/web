package com.chunfeng.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充
 */
@Slf4j
@Component
public class MyBatisTimeConfig implements MetaObjectHandler {

    /**
     * 自动添加
     *
     * @param metaObject metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "userCreateTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "userUpdateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "orderCreateTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "orderUpdateTime", Date.class, new Date());
    }

    /**
     * 自动修改
     *
     * @param metaObject metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "userUpdateTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "orderUpdateTime", Date.class, new Date());
    }
}
