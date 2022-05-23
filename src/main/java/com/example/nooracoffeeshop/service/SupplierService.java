package com.example.nooracoffeeshop.service;

import java.util.List;
import java.util.Optional;

import com.example.nooracoffeeshop.model.Supplier;
import com.example.nooracoffeeshop.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }
    
    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }
    
    // public void addSupplier(String supplierName, String contactPerson, String contactPersonEmail) {
    //     Supplier supplier = new Supplier();
    //     supplier.setName(supplierName);
    //     supplier.setContactPerson(contactPerson);
    //     supplier.setContactPersonEmail(contactPersonEmail);
    //     supplierRepository.save(supplier);
    // }
    
    public void removeSupplierById(Long id) {
        supplierRepository.deleteById(id);
    }

    
  
}
