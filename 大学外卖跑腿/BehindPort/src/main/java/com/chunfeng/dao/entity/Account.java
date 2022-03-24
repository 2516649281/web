package com.chunfeng.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 账号表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @TableId(type = IdType.AUTO)
    private Integer accountId;//账号编号
    private String accountName;//账号名
    private String accountPassword;//账号密码
    @TableLogic
    private Integer accountDeleted;//账号状态
    private Date accountCreateTime;//创建时间
    private Date accountUpdateTime;//修改时间
    @TableField(exist = false)
    private String token;//登录验证token
    @TableField(exist = false)
    private User user;//用户信息
}
