package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.MaterialMapper;
import com.chunfeng.entity.Json;
import com.chunfeng.entity.Material;
import com.chunfeng.service.inter.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 物资业务层
 */
@Service
public class MaterialImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 数据
     */
    private Object data;

    /**
     * 运行状态
     */
    private Boolean status;

    /**
     * 查询数据量
     */
    private Long size;

    /**
     * 出库数量
     */
    private Long outSize;

    /**
     * 查询所有物资
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @Override
    public Json selectAll(int current, int size) {
        try {
            Page<Material> materialPage = materialMapper.selectAllMaterial(new Page<>(current, size));
            outSize = materialMapper.selectAllMaterialSize();
            this.size = materialPage.getTotal();
            data = materialPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size, outSize);
    }

    /**
     * 根据类别查询物资
     *
     * @param current          页码
     * @param size             每页显示数
     * @param materialCategory 物资类别
     * @return Json
     */
    @Override
    public Json selectAllByClass(int current, int size, String materialCategory) {
        try {
            Page<Material> materialPage = materialMapper.selectPage(new Page<>(current, size), new QueryWrapper<Material>().like("material_Category", materialCategory));
            this.size = materialPage.getTotal();
            outSize = materialMapper.selectAllMaterialSize();
            data = materialPage.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size, outSize);
    }

    /**
     * 添加物资信息
     *
     * @param material 需提供:物资名,物资类别,物资价格,来源地址
     * @return Json
     */
    @Override
    public Json insertMaterial(Material material) {
        try {
            data = materialMapper.insert(material);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

    /**
     * 修改物资信息
     *
     * @param material 需提供:物资id;可提供:物资名,物资类别,物资价格,来源地址
     * @return Json
     */
    @Override
    public Json updateMaterial(Material material) {
        try {
            data = materialMapper.updateById(material);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

    /**
     * 删除物资
     *
     * @param material 需提供:物资id
     * @return Json
     */
    @Override
    public Json deleteMaterial(Material material) {
        try {
            data = materialMapper.deleteById(material.getId());
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

    /**
     * 恢复物资
     *
     * @param material 需提供:编号
     * @return Json
     */
    @Override
    public Json updateMaterialStatus(Material material) {
        try {
            data = materialMapper.updateMaterialStatus(material);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }
}
