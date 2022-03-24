package com.chunfeng.Login.dao;

import com.chunfeng.service.inter.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountTest {

    @Autowired
    private AccountService accountService;

    @Test
    void selectAll() {
        System.out.println(accountService.selectAllAccount(1, 10));
    }

    @Test
    void selectWrapper() {
        System.out.println(accountService.selectAccountWrapper(1, 10, "2"));
    }
}
