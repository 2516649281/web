package com.chunfeng.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    @TableId(type = IdType.AUTO)
    private int Id;//编号
    private String ClassName;//班级名称
}
