package com.chunfeng.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 班级实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    @TableId(type = IdType.AUTO)
    private Long classId;//班级编号
    private String className;//班级名称
    @TableField(exist = false)
    private List<Work> classWorks;//班级作业
}
