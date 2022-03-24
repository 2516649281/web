package com.chunfeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunfeng.dao.entity.Json;
import com.chunfeng.dao.entity.Order;
import com.chunfeng.dao.mapper.OrderMapper;
import com.chunfeng.dao.mapper.UserMapper;
import com.chunfeng.service.inter.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账单业务实现
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    /**
     * 订单业务层
     */
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 用户编号
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 运行状态
     */
    private Boolean status;

    /**
     * 数据
     */
    private Object data;

    /**
     * 查询总数
     */
    private Long pageSize;

    /**
     * 查询所有订单
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @Override
    public Json selectAllOrder(int current, int size) {
        try {
            Page<Order> orderPage = orderMapper.selectPage(new Page<>(current, size), null);
            getUserName(orderPage);
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, pageSize);
    }

    /**
     * 模糊查询订单
     *
     * @param current   页码
     * @param size      每页显示数
     * @param orderName 订单名
     * @return Json
     */
    @Override
    public Json selectLikeOrder(int current, int size, String orderName) {
        try {
            Page<Order> orderPage = orderMapper.selectPage(new Page<>(current, size), new QueryWrapper<Order>().like("order_Name", orderName));
            getUserName(orderPage);
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, pageSize);
    }

    /**
     * 根据跑腿员编号查询订单
     *
     * @param current 页码
     * @param size    每页显示数
     * @param runId   跑腿员编号
     * @return Json
     */
    @Override
    public Json selectAllByRunId(int current, int size, Long runId) {
        try {
            Page<Order> orderPage = orderMapper.selectPage(new Page<>(current, size), new QueryWrapper<Order>().eq("run_id", runId));
            getUserName(orderPage);
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, pageSize);
    }

    /**
     * 根据客户编号查询订单
     *
     * @param current 页码
     * @param size    每页显示数
     * @param userId  用户编号
     * @return Json
     */
    @Override
    public Json selectAllUserId(int current, int size, Long userId) {
        try {
            Page<Order> orderPage = orderMapper.selectPage(new Page<>(current, size), new QueryWrapper<Order>().eq("user_id", userId));
            getUserName(orderPage);
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, pageSize);
    }

    /**
     * 添加订单
     *
     * @param order 需提供:订单名,发起人编号;可提供:管理员(接单员)编号,跑腿员编号
     * @return Json
     */
    @Override
    public Json insertOrder(Order order) {
        try {
            data = orderMapper.insert(order) > 0;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 修改订单
     *
     * @param order 需提供:订单编号;可提供:订单名,管理员(接单员)编号,跑腿员编号,发起人编号
     * @return Json
     */
    @Override
    public Json updateOrderById(Order order) {
        try {
            data = orderMapper.updateById(order);
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 删除订单
     *
     * @param order 需提供:订单编号
     * @return Json
     */
    @Override
    public Json deleteOrderById(Order order) {
        try {
            data = orderMapper.deleteById(order.getOrderId()) > 0;
            status = true;
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return new Json(status, data, null);
    }

    /**
     * 以编号获取名称
     *
     * @param orderPage 分页类
     */
    private void getUserName(Page<Order> orderPage) {
        List<Order> records = orderPage.getRecords();
        for (Order record : records) {
            if (record.getUserId() != null) {//获取发起人
                record.setUser(userMapper.selectById(record.getUserId()));
            }
            if (record.getAdminId() != null) {//获取接单员
                record.setAdmin(userMapper.selectById(record.getAdminId()));
            }
            if (record.getRunId() != null) {//获取跑腿员
                record.setRun(userMapper.selectById(record.getRunId()));
            }
        }
        data = records;
        pageSize = orderPage.getTotal();
        status = true;
    }
}
