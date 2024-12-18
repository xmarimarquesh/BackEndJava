package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.TokenLogin;
import com.example.demo.services.LoginService;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
@RequestMapping("/login")
public class Login {

    @Autowired
    LoginService login_Service;

    @PostMapping
    public ResponseEntity<TokenLogin> login(@RequestBody LoginDto login){

        String result = login_Service.login(login.login(), login.password());

        if(result != null)
            return new ResponseEntity<>(new TokenLogin("Usuario logado", result), HttpStatus.OK);
        
        return new ResponseEntity<>(new TokenLogin("Login incorreto", "null"), HttpStatus.BAD_REQUEST);
    }
}
