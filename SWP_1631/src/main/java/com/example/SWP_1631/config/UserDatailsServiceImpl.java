package com.example.SWP_1631.config;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Role;
import com.example.SWP_1631.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDatailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository acc;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account a = acc.getAccByEmail(username);
        if (a == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        Role role = acc.getRoleByEmail(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(String.valueOf(role));
        grantList.add(authority);
//Cuối cùng mình tạo đối tượng UserDetails của Spring và mình cung cấp cá thông số như tên , password và quyền
        // Đối tượng userDetails sẽ chứa đựng các thông tin cần thiết về user từ đó giúp Spring Security quản lý được phân quyền như ta đã
        // cấu hình trong bước 4 method configure
        UserDetails userDetails = new User(a.getEmail(),
                a.getPassword(), grantList);
        return userDetails;
    }
}
