package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dto.Token;
import com.example.demo.implementations.DefaultJWTService;
import com.example.demo.implementations.LoginBack;
import com.example.demo.implementations.ReverseImpl;
import com.example.demo.services.JWTService;
import com.example.demo.services.LoginService;
import com.example.demo.services.PasswordService;
import com.example.demo.services.ReverseService;

@Configuration
public class DependencyConfiguration {
    @Bean
    @Scope("prototype")
    public LoginService loginService(){
        return new LoginBack();
    }

    @Bean
    public ReverseService reverseService(){
        return new ReverseImpl();
    }

    @Bean
    public PasswordService passwordService() {
        return new PasswordService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public JWTService<Token> jwtService() {
        return new DefaultJWTService();
    }
}
