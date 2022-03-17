package com.example.nooracoffeeshop.service;

import java.util.List;
import java.util.Optional;

import com.example.nooracoffeeshop.model.Category;
import com.example.nooracoffeeshop.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category>getAllCategory(){
        return categoryRepository.findAll(); 
    }

     public void addCategory(Category category) {
         categoryRepository.save(category);
        
     }

     public void removeCategoryById(Long id) {
         categoryRepository.deleteById(id);
     }
        


    public Optional<Category> getCategoryById(long id) {
       
        return categoryRepository.findById(id);
    }
   
    

    
}
