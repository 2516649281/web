package com.chunfeng.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chunfeng.entity.Administrator;
import com.chunfeng.entity.Json;

/**
 * 管理员service
 */
public interface AdministratorService extends IService<Administrator> {
    /**
     * 查询所有管理者
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    Json selectAll(int current, int size);

    /**
     * 根据地址字段查询所有管理者
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    Json selectAllByAddress(int current, int size, String address);

    /**
     * 添加管理者
     *
     * @param administrator 需提供:管理者姓名,管理者年龄,管理者性别,管理者电子邮件,管理者电话,管理者账号编号
     * @return Json
     */
    Json insertAdminById(Administrator administrator);

    /**
     * 修改管理者
     *
     * @param administrator 需提供:管理者编号;可提供:管理者姓名,管理者年龄,管理者性别,管理者电子邮件,管理者电话,管理者账号编号
     * @return Json
     */
    Json updateAdminById(Administrator administrator);

    /**
     * 删除管理者
     *
     * @param administrator 需提供:管理者编号
     * @return Json
     */
    Json deleteAdminById(Administrator administrator);
}
