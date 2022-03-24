package com.chunfeng.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;//用户编号
    private String userName;//用户姓名
    private Integer userAge;//用户年龄
    private String userSex;//用户性别
    private String userAddress;//用户住址
    private String userPhone;//用户手机号
    private Integer userDuty;//用户职务
    private Integer accountId;//账号编号
}
