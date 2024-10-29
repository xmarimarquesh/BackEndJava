package com.example.demo.controllers; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;

@RestController
@RequestMapping("/user")
public class UserController { 

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginData data){
        if(data.Login().equals("MarianaHipolito") && data.Password().equals("123"))
            return ResponseEntity.ok("Bem-vindo");
        return ResponseEntity.status(404).build();
    }
}
