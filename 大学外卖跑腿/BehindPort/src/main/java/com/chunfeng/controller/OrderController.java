package com.chunfeng.controller;

import com.chunfeng.dao.entity.Json;
import com.chunfeng.dao.entity.Order;
import com.chunfeng.service.inter.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单接口类
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 订单业务层
     */
    @Autowired
    private OrderService orderService;

    /**
     * 查询所有订单
     *
     * @param current 页码
     * @param size    每页显示数
     * @return Json
     */
    @GetMapping("/{current}/{size}")
    Json selectAllOrder(@PathVariable int current, @PathVariable int size) {
        return orderService.selectAllOrder(current, size);
    }

    /**
     * 模糊查询订单
     *
     * @param current   页码
     * @param size      每页显示数
     * @param orderName 订单名
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}/{orderName}", params = "order")
    Json selectAllLikeName(@PathVariable int current, @PathVariable int size, @PathVariable String orderName) {
        return orderService.selectLikeOrder(current, size, orderName);
    }

    /**
     * 根据跑腿员编号查询订单
     *
     * @param current 页码
     * @param size    每页显示数
     * @param runId   跑腿员编号
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}/{runId}", params = "run")
    Json selectALlByRunId(@PathVariable int current, @PathVariable int size, @PathVariable Long runId) {
        return orderService.selectAllByRunId(current, size, runId);
    }

    /**
     * 根据客户编号查询订单
     *
     * @param current 页码
     * @param size    每页显示数
     * @param userId  用户编号
     * @return Json
     */
    @GetMapping(value = "/{current}/{size}/{userId}", params = "user")
    Json selectALlByUserId(@PathVariable int current, @PathVariable int size, @PathVariable Long userId) {
        return orderService.selectAllUserId(current, size, userId);
    }


    /**
     * 添加订单
     *
     * @param order 需提供:订单名,发起人编号;可提供:管理员(接单员)编号,跑腿员编号
     * @return Json
     */
    @PostMapping
    Json insertOrder(@RequestBody Order order) {
        return orderService.insertOrder(order);
    }

    /**
     * 修改订单
     *
     * @param order 需提供:订单编号;可提供:订单名,管理员(接单员)编号,跑腿员编号,发起人编号
     * @return Json
     */
    @PutMapping
    Json updateOrderById(@RequestBody Order order) {
        return orderService.updateOrderById(order);
    }

    /**
     * 删除订单
     *
     * @param order 需提供:订单编号
     * @return Json
     */
    @DeleteMapping
    Json deleteOrderById(@RequestBody Order order) {
        return orderService.deleteOrderById(order);
    }
}
