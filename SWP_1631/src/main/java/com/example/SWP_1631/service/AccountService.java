package com.example.SWP_1631.service;


import com.example.SWP_1631.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {
    public List<Account> getListAccount();

    public Account getAccount(Account account);


    Account update(Integer id, Account user);

    void delete(Integer id);
    void save( Account user);
}
