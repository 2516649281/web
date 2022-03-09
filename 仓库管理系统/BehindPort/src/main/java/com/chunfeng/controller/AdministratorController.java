package com.chunfeng.controller;

import com.chunfeng.entity.Administrator;
import com.chunfeng.entity.Json;
import com.chunfeng.service.inter.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理者controller
 */
@RestController
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    /**
     * 查询所有管理者
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @GetMapping("/{current}/{size}")
    public Json selectAll(@PathVariable int current, @PathVariable int size) {
        return administratorService.selectAll(current, size);
    }

    /**
     * 根据地址字段查询所有管理者
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @GetMapping("/{current}/{size}/{address}")
    public Json selectAllByAddress(@PathVariable int current, @PathVariable int size, @PathVariable String address) {
        return administratorService.selectAllByAddress(current, size, address);
    }

    /**
     * 添加管理者
     *
     * @param administrator 需提供:管理者姓名,管理者年龄,管理者性别,管理者电子邮件,管理者电话,管理者账号编号
     * @return Json
     */
    @PostMapping
    public Json insertAdmin(@RequestBody Administrator administrator) {
        return administratorService.insertAdminById(administrator);
    }

    /**
     * 修改管理者
     *
     * @param administrator 需提供:管理者编号;可提供:管理者姓名,管理者年龄,管理者性别,管理者电子邮件,管理者电话,管理者账号编号
     * @return Json
     */
    @PutMapping
    public Json updateAdminById(@RequestBody Administrator administrator) {
        return administratorService.updateAdminById(administrator);
    }

    /**
     * 删除管理者
     *
     * @param administrator 需提供:管理者编号
     * @return Json
     */
    @DeleteMapping
    public Json deleteAdminById(@RequestBody Administrator administrator) {
        return administratorService.deleteAdminById(administrator);
    }
}
