package com.example.nooracoffeeshop.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor

public class Category extends AbstractPersistable<Long>{
    private String name;
    private long categoryID;
    
    // @OneToMany(mappedBy = "category")
    // private List<Product> products = new ArrayList<>();
}
