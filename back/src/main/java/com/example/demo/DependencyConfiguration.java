package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.implementations.ExampleLoginService;
import com.example.demo.implementations.ReverseImpl;
import com.example.demo.services.LoginService;
import com.example.demo.services.ReverseService;

@Configuration
public class DependencyConfiguration {
    @Bean
    @Scope("prototype")
    public LoginService loginService(){
        return new ExampleLoginService();
    }

    @Bean
    public ReverseService reverseService(){
        return new ReverseImpl();
    }
}
