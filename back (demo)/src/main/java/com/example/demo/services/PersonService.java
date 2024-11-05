package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;

@Service
public class PersonService {
    @Autowired
    PersonRepository repo;

    public Person savePerson(Person person){
        return repo.save(person);
    }
}
