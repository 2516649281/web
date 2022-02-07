package com.chunfeng.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @TableId(type = IdType.AUTO)
    private int Id;//编号
    private String StudentName;//学生姓名
    private Integer StudentAge;//学生年龄
    private String StudentSex;//学生性别
    private String StudentAddress;//学生住址
    private int ClassId;//班级编号
    @TableField(exist = false)//设置排除项
    private String ClassName;//班级名称
    @TableField(exist = false)
    private Long size;//数据总数
}
