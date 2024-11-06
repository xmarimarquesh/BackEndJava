package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Calculo;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
@RequestMapping("/imaexp")
public class Imaexp {

    @GetMapping
    public Calculo calculo(Double A, Double b){
        var re = A * Math.sin(b);
        var im = A * Math.cos(b);

        return new Calculo(re, im);
    }
}
