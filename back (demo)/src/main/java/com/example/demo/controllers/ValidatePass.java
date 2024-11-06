package com.example.demo.controllers;

public class ValidatePass {
    public static boolean validatePass(String pass){
        Boolean temMinuscula = pass.chars().anyMatch(Character::isLowerCase);
        Boolean temMaiuscula = pass.chars().anyMatch(Character::isUpperCase);
        Boolean temNumero = pass.chars().anyMatch(Character::isDigit);
        Boolean temCaractereEspecial = pass.chars().anyMatch(c -> "!@#$%^&*()_+{}[]|:;,.<>?".indexOf(c) >= 0);

        if(!temMinuscula || !temMaiuscula || !temNumero || !temCaractereEspecial || pass.length() < 4){
            return false;
        }

        return true;
    }
}
