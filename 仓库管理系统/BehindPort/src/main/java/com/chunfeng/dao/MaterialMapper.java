package com.chunfeng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chunfeng.entity.Material;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 物资mapper
 */
@Repository
public interface MaterialMapper extends BaseMapper<Material> {

    /**
     * 查询所有
     *
     * @param page 分页
     * @return Json
     */
    @Select("select * from material")
    Page<Material> selectAllMaterial(Page<Material> page);


    /**
     * 获取已出库物资数量
     *
     * @return int
     */
    @Select("select count(*) from material where material_Status=1")
    Long selectAllMaterialSize();

    /**
     * 恢复物资
     *
     * @param material 需提供:编号
     * @return Json
     */
    @Update("update material set material_Status = 0 where id=#{id};")
    int updateMaterialStatus(Material material);
}
