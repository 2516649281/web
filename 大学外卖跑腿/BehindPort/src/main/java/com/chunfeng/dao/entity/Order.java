package com.chunfeng.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 订单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("`order`")//设置表名防止报错
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer orderId;//订单编号
    private String orderName;//订单名
    private Integer userId;//发起人编号
    @TableField(exist = false)
    private User user;//发起人
    private Integer adminId;//接单员编号
    @TableField(exist = false)
    private User admin;//接单员
    private Integer runId;//跑腿员编号
    @TableField(exist = false)
    private User run;//跑腿员
    @TableField(fill = FieldFill.INSERT)
    private Date orderCreateTime;//创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date orderUpdateTime;//更新时间
}
