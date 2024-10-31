package com.example.demo.controllers; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.LoginService;

@RestController
@RequestMapping("/user")
public class UserController { 

    @Autowired
    LoginService service;
    UserRepository repo;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        var usuario = repo.findById(id);

        if(!usuario.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginData data){
        var id = service.login(data.Login(), data.Password());
        if(id == 1)
            return ResponseEntity.ok("Bem-vindo");
        return ResponseEntity.status(404).build();
    }
}
