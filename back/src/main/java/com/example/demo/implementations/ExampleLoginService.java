package com.example.demo.implementations;

import com.example.demo.services.LoginService;

public class ExampleLoginService implements LoginService {

    @Override
    public Integer login(String username, String password) {
        if(!username.equals("MarianaHipolito") || !password.equals("123"))
            return -1;
        return 1;
    }
    
}
