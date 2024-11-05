package com.example.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.LoginService;

public class LoginBack implements LoginService{
    @Autowired
    PersonRepository repo;

    @Override
    public Integer login(String username, String password) {
        Person pessoa = repo.findByName(username);
        
        if(pessoa.getPass().equals(password))
            return 1;
        
        return 0;
        
    }
    
}
