package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CityDto;
import com.example.demo.repositories.CityRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
@RequestMapping("/cities")
public class City {

    @Autowired
    CityRepository repo_city;

    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCity(){
        List<CityDto> list = new ArrayList<>();

        list = repo_city.findAll()
        .stream()
        .map(city -> new CityDto(city.getCidade(), city.getEstado(), city.getPais()))
        .collect(Collectors.toList());
        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<CityDto>> getAllCity(@PathVariable String query){
        List<CityDto> list = new ArrayList<>();

        list = repo_city.findByCidadeOrEstadoOrPais(query, query, query)
        .stream()
        .map(city -> new CityDto(city.getCidade(), city.getEstado(), city.getPais()))
        .collect(Collectors.toList());
        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
