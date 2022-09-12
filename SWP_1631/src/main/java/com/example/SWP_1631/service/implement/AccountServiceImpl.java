package com.example.SWP_1631.service.implement;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Role;
import com.example.SWP_1631.repository.AccountRepository;
import com.example.SWP_1631.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accRes;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void save(Account user) {
        accRes.save(user);
    }

    @Override
    public List<Account> getListAccount() {
        return accRes.findAll();
    }

    @Override
    public Account update(Integer id, Account user) {
        Account ac = accRes.findById(id).orElse(null);
        if (ac == null){
            return null;
        }
        ac.setAccountId(user.getAccountId());
        ac.setDob(user.getDob());
        ac.setAddress(user.getAddress());
        ac.setGender(user.isGender());
        ac.setFirstName(user.getFirstName());
        ac.setLastName(user.getLastName());
        ac.setEmail(user.getEmail());
        ac.setPassword(user.getPassword());
        ac.setPhoneNumber(user.getPhoneNumber());
        ac.setImg(user.getImg());
        ac.setRole(user.getRole());
        return accRes.save(ac);
    }

    @Override
    public void delete(Integer id) {
    accRes.deleteById(id);
    }

    @Override
    public Account getAccount(Account account) {
        return accRes.getAccByEmail(account.getEmail());
    }
}
