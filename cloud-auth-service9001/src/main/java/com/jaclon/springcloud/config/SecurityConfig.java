package com.jaclon.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * SpringSecurity配置类
 *
 * @author jaclon
 * @since 2023/11/14 11:06
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //basic 弹窗登录
                //.httpBasic()
                //表单登录
                .formLogin()
                .and()
                .csrf().disable();
    }

    @Bean
    public UserDetailsService userDetails() {
        UserDetails admin = User.withUsername("admin")
                //,默认BCryptPasswordEncoder 更多实现 org.springframework.security.crypto.password.PasswordEncoder
                //可查看该接口的实现
                // password  Spring Security 5.0开始必须以 {加密方式}+加密后的密码 这种格式填写
                .password("{bcrypt}$2a$10$ZlFDDZMkZ9P7Yb4BsZ50ZueNzn7yM3GTJD97M5cJMWDu4oKr1Lsuq")
                .roles("ADMIN", "USER")
                .build();
        UserDetails user = User.withUsername("user")
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode("123456"))
                .roles("USER")
                .build();
        //内存用户管理器
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(admin);
        userDetailsManager.createUser(user);
        return userDetailsManager;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
