// package com.example.demo.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.dto.PersonDto;
// import com.example.demo.services.PersonService;

// import org.springframework.web.bind.annotation.CrossOrigin;
// @CrossOrigin(origins = {"http://localhost:5257"})
// @RestController
// @RequestMapping("/user")
// public class User {

//     @Autowired
//     PersonService person_service;

//     @PostMapping
//     public ResponseEntity<String> createUser(@RequestBody PersonDto person){
//         if(person.email() == null || person.password() == null || person.username() == null)
//             return new ResponseEntity<>("Preencha todos os campos!", HttpStatus.BAD_REQUEST);
        
//         if (!ValidateEmail.validateEmail(person.email())) 
//             return new ResponseEntity<>("Email inválido! Modelo esperado: a@b.z", HttpStatus.BAD_REQUEST);

//         person_service.createPerson(person.email(), person.password(), person.username());

//         return new ResponseEntity<>("Usuario cadastrado! ", HttpStatus.OK);
//     } 
// }
