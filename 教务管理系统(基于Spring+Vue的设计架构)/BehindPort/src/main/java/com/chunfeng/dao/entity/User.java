package com.chunfeng.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Long userId;//编号
    private String userName;//用户名
    private String userPassword;//用户密码
    @TableField(exist = false)
    private String newUserName;//新用户名
    @TableField(exist = false)
    private String newUserPassWord;//新密码
    @TableLogic
    private Integer userDeleted;//冻结指数
    @TableField(fill = FieldFill.INSERT)
    private Date userCreateTime;//创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date userUpdateTime;//修改时间
    private Integer userIdentity;//用户身份
    private Integer userAdmin;//管理员标识
    @TableField(exist = false)
    private Long userIdentityId;//用户身份id
    @TableField(exist = false)
    private String userRealName;//用户真实姓名
    @TableField(exist = false)
    private String className;//用户班级
    @TableField(exist = false)
    private String address;
    @TableField(exist = false)
    private String Identity;//身份
    @TableField(exist = false)
    private String Admin;//权限
}
