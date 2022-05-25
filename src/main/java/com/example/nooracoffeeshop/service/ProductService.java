package com.example.nooracoffeeshop.service;

import java.util.List;
import java.util.Optional;

import com.example.nooracoffeeshop.model.Product;
import com.example.nooracoffeeshop.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product>getAllProduct(){
        return productRepository.findAll(); 
    }

    
    public Product getProduct(Long id) {
        return productRepository.getById(id);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }
    
    public void removeProductById(Long id) {
        productRepository.deleteById(id);
   }
   public Optional<Product> getProductById(Long id) {
       return productRepository.findById(id);
   }
   public List<Product> getAllProductByCategoryId(Long id) {
       return productRepository.findAllByCategory_Id(id);
       }

       public Page<Product> getProductPaginate(int currentPage, int size) {
        PageRequest pageable = PageRequest.of(currentPage, size);
        return productRepository.findAll(pageable);
    }

   
    
}
