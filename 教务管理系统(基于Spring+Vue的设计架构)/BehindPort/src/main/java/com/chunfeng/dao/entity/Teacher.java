package com.chunfeng.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教师实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @TableId(type = IdType.AUTO)
    private Long teacherId;//编号
    private String teacherName;//教师姓名
    private int teacherAge;//教师年龄
    private String teacherSex;//教师性别
    private String teacherAddress;//教师地址
    private Long userId;//用户编号
}
