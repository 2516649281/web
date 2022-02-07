package com.chunfeng.dao.pojo;

import lombok.Data;

/**
 * Json包装类
 */
@Data
public class Json {
    private boolean status;//运行状态
    private Object data;//数据

    public Json(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }
}
