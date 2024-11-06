package com.example.demo.controllers;

public class ValidateEmail {
    public static boolean validateEmail(String email){
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');
        
        if (atIndex < 1 || dotIndex < atIndex + 2 || dotIndex >= email.length() - 1) {
            return false;
        }

        return true;
    }
}
