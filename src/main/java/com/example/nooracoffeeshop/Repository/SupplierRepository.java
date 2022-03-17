package com.example.nooracoffeeshop.repository;

import com.example.nooracoffeeshop.model.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
}
