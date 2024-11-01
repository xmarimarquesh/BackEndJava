package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByName(String name);
} 
