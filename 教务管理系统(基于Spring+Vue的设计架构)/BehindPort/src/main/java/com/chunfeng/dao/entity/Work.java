package com.chunfeng.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作业实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Work {
    @TableId(type = IdType.AUTO)
    private Long workId;//作业编号
    private String workName;//作业名称
    private String workContent;//作业要求
    private Long teacherId;//教师编号
    private Long classId;//班级编号
    @TableField(exist = false)
    private String teacherName;//布置人
    @TableField(exist = false)
    private String className;//班级名
}
