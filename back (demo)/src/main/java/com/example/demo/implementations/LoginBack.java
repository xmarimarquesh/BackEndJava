package com.example.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.LoginService;
import com.example.demo.services.PasswordService;

public class LoginBack implements LoginService{
    @Autowired
    PersonRepository repo;

    @Autowired
    private PasswordService passwordService;

    @Override
    public Integer login(String username, String password) {
        Person pessoa = repo.findByName(username);
        
        if(passwordService.checkPassword(password, pessoa.getPass()))
            return 1;
        
        return 0;
        
    }
    
}
