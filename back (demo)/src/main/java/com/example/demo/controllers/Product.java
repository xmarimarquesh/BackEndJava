package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.JWTDto;
import com.example.demo.dto.Token;
import com.example.demo.model.Person;
import com.example.demo.model.ProductModel;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.JWTService;

@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
@RequestMapping("/product")
public class Product {

    @Autowired
    PersonRepository repo;

    @Autowired
    JWTService<Token> jwtService;

    @Autowired
    ProductRepository repo_prod;

    @PostMapping
    private ResponseEntity<String> create(
        @RequestAttribute("token") Token token,
        @RequestBody JWTDto data){

        Long id = token.getId();

        Person person = repo.findById(id).get();

        System.out.println("ID: "+ id);
        System.out.println("Token: "+ token);

        if(!person.getEmail().equals("loja@loja.com"))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        
        ProductModel product = new ProductModel();
        product.setTitle(data.title());
        product.setValue(data.value());

        repo_prod.save(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
