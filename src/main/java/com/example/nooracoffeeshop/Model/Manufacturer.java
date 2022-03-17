package com.example.nooracoffeeshop.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Manufacturer extends AbstractPersistable<Long> {

    private String url;
    private String name;
    
}
