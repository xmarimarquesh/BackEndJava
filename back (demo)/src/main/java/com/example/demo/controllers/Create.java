package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PersonDto;
import com.example.demo.model.Person;
import com.example.demo.services.PersonService;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
@RequestMapping("/create")
public class Create {

    @Autowired
    PersonService person_service;

    @PostMapping
    public String createAccount(@RequestBody PersonDto person){

        if(person.email() == null || person.password() == null || person.username() == null){
            return "Preencha todos os campos!";
        }

        if(!ValidatePass.validatePass(person.password())){
            return "Sua senha deve conter ao menos 4 caracteres entre eles numeros, letras maiusculas, minusculas e caracteres especiais.";
        }
        
        if (!ValidateEmail.validateEmail(person.email())) {
            return "Email inválido! Modelo esperado: a@b.z";
        }

        Person person_model = new Person();
        person_model.setEmail(person.email());
        person_model.setName(person.username());
        person_model.setPass(person.password());

        person_service.savePerson(person_model);

        return "Conta criada com sucesso!";
    }
}
