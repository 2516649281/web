package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.AdministratorMapper;
import com.chunfeng.entity.Administrator;
import com.chunfeng.entity.Json;
import com.chunfeng.service.inter.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员业务层
 */
@Service
public class AdministratorImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;
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
     * 查询所有管理者
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @Override
    public Json selectAll(int current, int size) {
        try {
            Page<Administrator> page = administratorMapper.selectPage(new Page<>(current, size), null);
            this.size = page.getTotal();
            data = page.getRecords();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size, null);
    }

    /**
     * 根据地址字段查询所有管理者
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @Override
    public Json selectAllByAddress(int current, int size, String address) {
        try {
            Page<Administrator> page = administratorMapper.selectPage(new Page<>(current, size), new QueryWrapper<Administrator>().like("admin_Address", address));
            data = page.getRecords();
            this.size = page.getTotal();
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, this.size, null);
    }

    /**
     * 添加管理者
     *
     * @param administrator 需提供:管理者姓名,管理者年龄,管理者性别,管理者电子邮件,管理者电话,管理者账号编号
     * @return Json
     */
    @Override
    public Json insertAdminById(Administrator administrator) {
        try {
            data = administratorMapper.insert(administrator);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

    /**
     * 修改管理者
     *
     * @param administrator 需提供:管理者编号;可提供:管理者姓名,管理者年龄,管理者性别,管理者电子邮件,管理者电话,管理者账号编号
     * @return Json
     */
    @Override
    public Json updateAdminById(Administrator administrator) {
        try {
            data = administratorMapper.updateById(administrator);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

    /**
     * 删除管理者
     *
     * @param administrator 需提供:管理者编号
     * @return Json
     */
    @Override
    public Json deleteAdminById(Administrator administrator) {
        try {
            data = administratorMapper.deleteById(administrator.getId());
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null, null);
    }

}
