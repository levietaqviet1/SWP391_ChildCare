package com.example.SWP_1631.service;


import com.example.SWP_1631.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface AccountService extends UserDetailsService {
    public List<Account> getListAccount();

    public Optional<Account> getAccount(Integer id);


    Account update(Integer id, Account user);

    void delete(Integer id);
    void save( Account user);
}
