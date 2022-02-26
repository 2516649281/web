package com.chunfeng.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Json包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Json {
    private boolean status;//状态信息
    private Object data;//数据
    private Long size;//查询总数

}
