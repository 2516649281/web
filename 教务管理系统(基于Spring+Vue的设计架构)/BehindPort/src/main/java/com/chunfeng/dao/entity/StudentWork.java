package com.chunfeng.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生-作业联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWork {
    @TableId(type = IdType.AUTO)
    private Long studentWorkId;//学生作业编号
    private Long studentId;//学生编号
    private Long workId;//作业编号
    private String studentWork;//学生作业
    private Double studentSource;//作业分数
    @TableField(exist = false)
    private String studentName;//学生姓名
}
