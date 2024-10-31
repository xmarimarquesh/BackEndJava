package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SumResult;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/add")
    public Integer testAdd(Integer a, Integer b){
        return a + b;
    }

    @GetMapping("/{value}")
    public SumResult testPop(@PathVariable Integer value, Integer otherValue){
        if(otherValue == null){
            otherValue = 2;
        }
        
        var result = value - otherValue; 
        var isEven = result % 2 == 0;

        return new SumResult(result, isEven);
    }
}
