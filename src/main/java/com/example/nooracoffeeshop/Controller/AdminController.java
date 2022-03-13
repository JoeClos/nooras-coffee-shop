package com.example.nooracoffeeshop.Controller;

import java.util.Locale.Category;

import com.example.nooracoffeeshop.Service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    // @Autowired
    // private CategoryService categoryService;

    @GetMapping("/admin")
    public String adminHome(){
    return "adminHome";
    }

    @GetMapping("/admin/categories") 
        public String viewCategories() {
            // model.addAttribute("categories", categoryService.getAllCategory());
            return "categories";
    }

    // @GetMapping("/admin/categories/add") 
    //     public String viewAddForm(Model model) {
    //         Category category = new Category();
    //         model.addAttribute("category",  category);
    //         return "categoriesAdd";
    // }

    // @PostMapping("/admin/categories/add") 
    //     public String saveCategory(@ModelAttribute("category") Category category ) {
    //         categoryService.saveCategory(category);
    //         return "redirect/:admin/categories";
    // }

      @GetMapping("/admin/products") 
          public String viewProducts() {
              return "products";
      }
}