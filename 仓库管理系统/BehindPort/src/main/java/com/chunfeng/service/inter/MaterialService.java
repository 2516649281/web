package com.chunfeng.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chunfeng.entity.Json;
import com.chunfeng.entity.Material;

/**
 * 物资service
 */
public interface MaterialService extends IService<Material> {

    /**
     * 查询所有物资
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    Json selectAll(int current, int size);

    /**
     * 根据类别查询物资
     *
     * @param current          页码
     * @param size             每页显示数
     * @param materialCategory 物资类别
     * @return Json
     */
    Json selectAllByClass(int current, int size, String materialCategory);

    /**
     * 添加物资信息
     *
     * @param material 需提供:物资名,物资类别,物资价格,来源地址
     * @return Json
     */
    Json insertMaterial(Material material);

    /**
     * 修改物资信息
     *
     * @param material 需提供:物资id;可提供:物资名,物资类别,物资价格,来源地址
     * @return Json
     */
    Json updateMaterial(Material material);

    /**
     * 删除物资
     *
     * @param material 需提供:物资id
     * @return Json
     */
    Json deleteMaterial(Material material);

    /**
     * 恢复物资
     *
     * @param material 需提供:编号
     * @return Json
     */
    Json updateMaterialStatus(Material material);
}
