package com.chunfeng.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Json包装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Json {
    private Boolean msg;//状态
    private Object data;//数据
    private Long pageSize;//查询结果数
}
