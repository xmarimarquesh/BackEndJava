package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;
import com.example.demo.dto.Token;
import com.example.demo.dto.UserData;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.JWTService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repo;

    @Autowired
    RoleRepository rolesRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTService<Token> jwtService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserData userData) {

        if (userData.email() == null || userData.password() == null || userData.username() == null) {
            return new ResponseEntity<>(
                "username, email and password are expected.", 
                HttpStatus.BAD_REQUEST
            );
        }

        if (!repo.findByEmail(userData.email()).isEmpty()) {
            return new ResponseEntity<>(
                "email already is in use.", 
                HttpStatus.BAD_REQUEST
            );
        }

        if (!repo.findByUsername(userData.username()).isEmpty()) {
            return new ResponseEntity<>(
                "username already is in use.", 
                HttpStatus.BAD_REQUEST
            );
        }

        var freeRole = rolesRepo.findAll()
            .stream()
            .filter(r -> r.getName().equals("Free"))
            .findAny();
        
        if (!freeRole.isPresent()) {
            return new ResponseEntity<>(
                "free users cannot be created.", 
                HttpStatus.BAD_REQUEST
            );
        }
        
        var securePass = encoder.encode(userData.password());

        Usuario user = new Usuario();
        user.setEmail(userData.email());
        user.setUsername(userData.username());
        user.setPassword(securePass);
        user.setRole(freeRole.get());
        repo.save(user);

        return new ResponseEntity<>(
            "user create successfully",
            HttpStatus.CREATED
        );

    }

    @PostMapping("/login")
    public ResponseEntity<String> create(@RequestBody LoginData loginData) {

        if (loginData.login() == null || loginData.password() == null) {
            return new ResponseEntity<>(
                "login and password are expected.", 
                HttpStatus.BAD_REQUEST
            );
        }
        var users = repo.login(loginData.login());

        if (users.isEmpty()) {
            return new ResponseEntity<>(
                "The user do not exists.", 
                HttpStatus.UNAUTHORIZED
            );
        }
        var user = users.get(0);

        if (!encoder.matches(loginData.password(), user.getPassword())) {
            return new ResponseEntity<>(
                "The password is incorrect.", 
                HttpStatus.UNAUTHORIZED
            );
        }

        Token token = new Token();
        token.setId(user.getId());
        token.setRole(user.getRole().getName());
        
        var jwt = jwtService.get(token);

        return new ResponseEntity<>(
            jwt,
            HttpStatus.OK
        );
    }

}