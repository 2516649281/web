package com.chunfeng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chunfeng.entity.Material;
import com.chunfeng.service.inter.MaterialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MaterialTest {

    @Autowired
    private MaterialService materialService;

    /**
     * 查询全部
     */
    @Test
    public void selectAll() {
        materialService.list().forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    public void selectAllPage() {
        materialService.page(new Page<>(1, 10)).getRecords().forEach(System.out::println);
    }

    /**
     * 添加
     */
    @Test
    public void insert() {
        Material material = new Material();
        material.setMaterialName("太阳能动力组件");
        material.setMaterialCategory("电力");
        material.setMaterialPrice(89);
        material.setMaterialAddress("上海商贸");
        System.out.println(materialService.save(material));
    }

    /**
     * 修改
     */
    @Test
    public void update() {
        Material material = new Material();
        material.setId(101L);
        material.setMaterialAddress("北京商贸");
        System.out.println(materialService.updateById(material));
    }

    /**
     * 删除
     */
    @Test
    public void delete() {
        Material material = new Material();
        material.setId(100L);
        System.out.println(materialService.removeById(material.getId()));
    }
}
