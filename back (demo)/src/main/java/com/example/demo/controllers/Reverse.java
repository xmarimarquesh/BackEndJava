package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ReverseService;

import com.example.demo.dto.ReverseDto;

@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
@RequestMapping("/reverse")
public class Reverse {

    @Autowired
    ReverseService reverseService;

    @GetMapping("/{word}")
    public ReverseDto reverse(@PathVariable String word){
        var palavraInvertida = reverseService.reverse(word);
        if(palavraInvertida.equals(word))
            return new ReverseDto(palavraInvertida, true);
        return new ReverseDto(palavraInvertida, false);
    }
}
