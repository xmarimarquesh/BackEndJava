package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Vehicle;
import com.example.demo.repositories.VehicleRepository;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    VehicleRepository repo;

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable Long id){
        var vehicle = repo.findById(id);

        if(!vehicle.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
    }

    @GetMapping("/find/{type}")
    public ResponseEntity<List<Vehicle>> getByType(@PathVariable String type){
        var vehicles = repo.findByType(type);

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
}
