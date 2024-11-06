package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.User7;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.PersonService;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
@RequestMapping("/changepassword")
public class ChangePassword {

    @Autowired
    PersonRepository person_repositorio;

    @Autowired
    PersonService person_service;

    @PatchMapping
    public ResponseEntity<String> atualizarSenha(@RequestBody User7 user){

        if(!user.newPassword().equals(user.repeatPassword())){
            return new ResponseEntity<>("Senhas não correspondem", HttpStatus.BAD_REQUEST);
        }
        
        if(!ValidatePass.validatePass(user.newPassword())){
            return new ResponseEntity<>("Sua nova senha deve conter ao menos 4 caracteres entre eles numeros, letras maiusculas, minusculas e caracteres especiais.", HttpStatus.BAD_REQUEST);
        }
        
        Person person = person_repositorio.findByName(user.username());

        if(!user.password().equals(person.getPass())){
            return new ResponseEntity<>("Senha incorreta", HttpStatus.BAD_REQUEST);
        }
        
        if(person.equals(null)){
            return new ResponseEntity<>("Usuario não encontrado", HttpStatus.BAD_REQUEST);
        }

        person.setPass(user.newPassword());
        person_service.savePerson(person);

        return new ResponseEntity<>("Senha alterada com sucesso!", HttpStatus.OK);

    }
}
