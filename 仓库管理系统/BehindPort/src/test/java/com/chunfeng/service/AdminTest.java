package com.chunfeng.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chunfeng.entity.Administrator;
import com.chunfeng.service.inter.AdministratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminTest {

    @Autowired
    private AdministratorService administratorService;

    /**
     * 查看所有仓库管理员用户
     */
    @Test
    public void selectAll() {
        administratorService.list().forEach(System.out::println);
    }

    /**
     * 模糊查询
     */
    @Test
    public void selectAllByAddress() {
        String address = "A";
        administratorService.list(new QueryWrapper<Administrator>().like("admin_address", address)).forEach(System.out::println);
    }

    /**
     * 添加管理者
     */
    @Test
    public void insertAdmin() {
        Administrator administrator = new Administrator();
        administrator.setAdminName("风清杨");
        administrator.setAdminAge(45);
        administrator.setAdminSex("男");
        administrator.setAdminAddress("上海");
        administrator.setAdminEmail("2546987456@web.com");
        administrator.setAdminPhone("15863981258");
        System.out.println(administratorService.save(administrator));
    }

    /**
     * 修改管理者
     */
    @Test
    public void updateAdmin() {
        Administrator administrator = new Administrator();
        administrator.setId(6L);
        administrator.setUserId(6L);
        System.out.println(administratorService.updateById(administrator));
    }

    /**
     * 删除管理者
     */
    @Test
    public void deleteAdmin() {
        Administrator administrator = new Administrator();
        administrator.setId(6L);
        System.out.println(administratorService.removeById(administrator.getId()));
    }

}
