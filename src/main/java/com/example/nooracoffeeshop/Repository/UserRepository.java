package com.example.nooracoffeeshop.repository;

import java.util.Optional;

import com.example.nooracoffeeshop.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>  {
    
    Optional<User> findUserByEmail(String email);
}
