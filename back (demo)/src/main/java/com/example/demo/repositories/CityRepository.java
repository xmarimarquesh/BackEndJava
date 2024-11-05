package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCidadeOrEstadoOrPais(String query, String query2, String query3);
}
