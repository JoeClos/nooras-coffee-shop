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
public class Supplier  extends AbstractPersistable<Long>{

    private String name;
    private String contactPerson;
    private String contactPersonEmail;
    
}
