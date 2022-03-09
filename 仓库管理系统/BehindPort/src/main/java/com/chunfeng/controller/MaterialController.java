package com.chunfeng.controller;

import com.chunfeng.entity.Json;
import com.chunfeng.entity.Material;
import com.chunfeng.service.inter.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 物资mapper
 */
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    /**
     * 查询所有物资
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @GetMapping("/{current}/{size}")
    public Json selectAll(@PathVariable int current, @PathVariable int size) {
        return materialService.selectAll(current, size);
    }

    /**
     * 根据类别查询物资
     *
     * @param current  页码
     * @param size     每页显示数
     * @param Category 物资类别
     * @return Json
     */
    @GetMapping("{current}/{size}/{Category}")
    public Json selectAllByClass(@PathVariable int current, @PathVariable int size, @PathVariable String Category) {
        return materialService.selectAllByClass(current, size, Category);
    }

    /**
     * 添加物资信息
     *
     * @param material 需提供:物资名,物资类别,物资价格,来源地址
     * @return Json
     */
    @PostMapping
    public Json insertMaterial(@RequestBody Material material) {
        return materialService.insertMaterial(material);
    }

    /**
     * 修改物资信息
     *
     * @param material 需提供:物资id;可提供:物资名,物资类别,物资价格,来源地址
     * @return Json
     */
    @PutMapping
    public Json updateMaterial(@RequestBody Material material) {
        return materialService.updateMaterial(material);
    }

    /**
     * 删除物资
     *
     * @param material 需提供:物资id
     * @return Json
     */
    @DeleteMapping
    public Json deleteMaterial(@RequestBody Material material) {
        return materialService.deleteMaterial(material);
    }

    /**
     * 恢复物资
     *
     * @param material 需提供:编号
     * @return Json
     */
    @PutMapping(params = "recover")
    public Json updateMaterialStatus(@RequestBody Material material) {
        return materialService.updateMaterialStatus(material);
    }

}
