package com.example.nooracoffeeshop.repository;


import java.util.List;

import com.example.nooracoffeeshop.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
     List<Product> findAllByCategory_id(Long id);
     List<Product> findAllBySupplier_id(Long id);
     List<Product> findAllByManufacturer_id(Long id);

     List<Product> findAllByCategory_Id(Long categoryId);


    
}
