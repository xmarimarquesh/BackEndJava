package com.example.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.Token;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.JWTService;
import com.example.demo.services.LoginService;
import com.example.demo.services.PasswordService;

public class LoginBack implements LoginService{
    @Autowired
    PersonRepository repo;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    JWTService<Token> jwtService;

    @Override
    public String login(String username, String password) {
        Person pessoa = repo.findByName(username);

        Token token = new Token();
        token.setId(pessoa.getId());
        token.setName(pessoa.getName());
        
        var jwt = jwtService.get(token);
        
        if(passwordService.checkPassword(password, pessoa.getPass()))
            return String.valueOf(jwt);
        
        return null;
        
    }
    
}
