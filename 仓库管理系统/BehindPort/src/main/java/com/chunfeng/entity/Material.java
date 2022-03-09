package com.chunfeng.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 物资实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @TableId(type = IdType.AUTO)
    private Long id;//编号
    private String materialName;//物资名称
    private String materialCategory;//物资类别
    private Integer materialPrice;//物资价格
    @TableField(fill = FieldFill.INSERT)
    private Date inTime;//入库时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date outTime;//出库时间
    private String materialAddress;//来源地址
    @TableLogic
    private Integer materialStatus;//状态 如果为0则存在库中，如为1则已出库
}
