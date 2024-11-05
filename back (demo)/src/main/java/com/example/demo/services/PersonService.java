package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    PersonRepository repo;

    @Autowired
    private PasswordService passwordService;


    public Person savePerson(Person person){
        return repo.save(person);
    }

    public Person createPerson(String email, String password, String name) {

        String encryptedPassword = passwordService.encryptPassword(password);
        
        Person user = new Person();
        user.setEmail(email);
        user.setName(name);
        user.setPass(encryptedPassword);
        
        return repo.save(user);
    }
}
