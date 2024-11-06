package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PasswordRequest;

@Repository
public interface PasswordRequestRepository extends JpaRepository<PasswordRequest, Long> {
    
}