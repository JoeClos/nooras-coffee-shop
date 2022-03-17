package com.example.nooracoffeeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.nooracoffeeshop.service.CategoryService;
import com.example.nooracoffeeshop.service.ManufacturerService;
import com.example.nooracoffeeshop.service.ProductService;
import com.example.nooracoffeeshop.service.SupplierService;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    ProductService productService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    SupplierService supplierService;

    @GetMapping({"/", "/home"})
    public String home (Model model) {
        return "index";
    }

    @GetMapping("shop")
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    }
    
    @GetMapping("shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable Long id) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        model.addAttribute("manufacturer", manufacturerService.getAllManufacturer());
        model.addAttribute("suppliers", supplierService.getAllSupplier());
        return "shop";
    }
    
    @GetMapping("shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.getProductById(id).get());
        
        return "viewProduct";
    }
    
}
