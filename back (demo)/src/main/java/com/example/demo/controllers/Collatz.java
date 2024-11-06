package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Current;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
@RequestMapping("/collatz")
public class Collatz {

    @GetMapping
    public ResponseEntity<Current> current(Integer current, Integer step){
        if (current < 0 || step < 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        for(int i = 0; i < step; i++){
            current = collatz(current);
        }

        return ResponseEntity.ok(new Current(current));
    }

    public Integer collatz(Integer curr){
        if(curr % 2 == 0)
            return curr / 2;
        return 3 * curr + 1;
    }

}
