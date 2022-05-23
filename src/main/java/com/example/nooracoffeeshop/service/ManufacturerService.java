package com.example.nooracoffeeshop.service;

import java.util.List;
import java.util.Optional;

import com.example.nooracoffeeshop.model.Manufacturer;
import com.example.nooracoffeeshop.repository.ManufacturerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;
    public List<Manufacturer> getAllManufacturer() {
        return manufacturerRepository.findAll();
    }
    public void addManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }   

    public void removeManufacturerById(Long id) {
        manufacturerRepository.deleteById(id);
    }
    public Optional<Manufacturer> getManufacturerById(Long id) {
        return manufacturerRepository.findById(id);
    }
}
