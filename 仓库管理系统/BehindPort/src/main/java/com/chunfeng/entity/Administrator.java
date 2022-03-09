package com.chunfeng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {
    @TableId(type = IdType.AUTO)
    private Long id;//编号
    private String adminName;//管理员姓名
    private Integer adminAge;//管理员年龄
    private String adminSex;//管理员性别
    private String adminAddress;//管理员住址
    private String adminEmail;//管理员邮箱
    private String adminPhone;//管理员电话
    private Long userId;//用户编号
}
