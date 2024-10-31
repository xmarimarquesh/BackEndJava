package com.example.demo.implementations;

import com.example.demo.services.ReverseService;

public class ReverseImpl implements ReverseService {

    @Override
    public String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }
    
}
