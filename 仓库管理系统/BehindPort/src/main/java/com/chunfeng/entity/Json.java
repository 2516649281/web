package com.chunfeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json包装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Json {
    private Boolean status;//执行状态
    private Object data;//数据
    private Long size;//查询数据量
    private Long outSize;//出库数量
}
