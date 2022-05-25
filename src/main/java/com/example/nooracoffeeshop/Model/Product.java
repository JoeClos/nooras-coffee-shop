package com.example.nooracoffeeshop.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Product extends AbstractPersistable<Long>{
    private String name;
    private String description;
    private double price;
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;



    @ManyToOne
    // @ToString.Exclude
    // @JoinColumn(name = "supplier_id")
    private Supplier supplier;
        
    @ManyToOne
    private Manufacturer manufacturer;
    
}
