package com.example.SWP_1631.config;

import com.example.SWP_1631.service.implement.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
//        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("admin")
//                .antMatchers("/", "/login", "/logout").permitAll()
//                .anyRequest().permitAll();

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginProcessingUrl("/authenticateTheUser") // Bạn còn nhớ bước 3 khi tạo form login thì action của nó là j_spring_security_check giống ở
                .loginPage("/home/login")//
                .defaultSuccessUrl("/home/loginSuccess")//đây Khi đăng nhập thành công thì vào trang này. userAccountInfo sẽ được khai báo trong controller để hiển thị trang view tương ứng
                .failureUrl("/home/login")// Khi đăng nhập sai username và password thì nhập lại
                .usernameParameter("email")// tham số này nhận từ form login ở bước 3 có input  name='username'
                .passwordParameter("password")// tham số này nhận từ form login ở bước 3 có input  name='password'
                // Cấu hình cho Logout Page. Khi logout mình trả về trang
                .and().logout().logoutUrl("/home/logoutSuccessful").logoutSuccessUrl("/home/");

    }
}
