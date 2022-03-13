package com.example.nooracoffeeshop.Repository;



import com.example.nooracoffeeshop.Model.Category;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepository extends JpaRepository<Category, Long> {

    // void save(java.util.Locale.Category category);
    
}
