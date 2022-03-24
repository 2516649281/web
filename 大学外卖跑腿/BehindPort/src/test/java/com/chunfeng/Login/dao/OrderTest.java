package com.chunfeng.Login.dao;

import com.chunfeng.service.inter.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderService orderService;

    @Test
    void selectAll() {
        System.out.println(orderService.selectAllOrder(1, 10));
    }
}
