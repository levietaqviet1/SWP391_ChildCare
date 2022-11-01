package com.example.SWP_1631;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.service.AccountService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class Swp1631ApplicationTests {
    @Autowired
    private AccountService accountService;

    @Test
    void contextLoadsFirstNamById() {
        Account acc = new Account();
        acc.setFirstName("Jonah");
        Optional<Account> acc1 = accountService.getAccount(8);
        acc1.ifPresent(use -> Assert.assertEquals(true, use.getFirstName().equals(acc.getFirstName())));
    }

    @Test
    void contextLoadsGmailById() {
        Account acc = new Account();
        acc.setEmail("jonah@gmail.com");
        Optional<Account> acc1 = accountService.getAccount(8);
        acc1.ifPresent(use -> Assert.assertEquals(true, use.getEmail().equals(acc.getEmail())));
    }

}
