package com.example.nooracoffeeshop.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private Long categoryId;
}
