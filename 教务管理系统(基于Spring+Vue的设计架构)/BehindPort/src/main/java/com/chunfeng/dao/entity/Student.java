package com.chunfeng.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 学生实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @TableId(type = IdType.AUTO)
    private Long studentId;//编号
    private String studentName;//学生姓名
    private Long classId;//班级编号
    private Long userId;//用户编号
    @TableField(exist = false)
    private String className;//班级名称
    private Integer studentAge;//学生年龄
    private String studentSex;//学生性别
    private String studentAddress;//学生地址
    @TableField(exist = false)
    private List<StudentWork> studentWorks;//学生作业
    @TableField(exist = false)
    private String workSituation;//作业完成情况
}
