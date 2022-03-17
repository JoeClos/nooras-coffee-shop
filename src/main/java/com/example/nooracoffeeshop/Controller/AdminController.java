package com.example.nooracoffeeshop.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.example.nooracoffeeshop.model.Category;
import com.example.nooracoffeeshop.model.Manufacturer;
import com.example.nooracoffeeshop.model.Product;
import com.example.nooracoffeeshop.model.ProductDTO;
import com.example.nooracoffeeshop.model.Supplier;
import com.example.nooracoffeeshop.repository.CategoryRepository;
import com.example.nooracoffeeshop.service.CategoryService;
import com.example.nooracoffeeshop.service.ManufacturerService;
import com.example.nooracoffeeshop.service.ProductService;
import com.example.nooracoffeeshop.service.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    SupplierService supplierService;

    @GetMapping("/admin")
    public String adminHome(){
    return "adminHome";
    }

    // Categories 

    @GetMapping("/admin/categories") 
        public String getCategories(Model model) {
             model.addAttribute("categories", categoryService.getAllCategory());
            return "categories";
    }

     @GetMapping("/admin/categories/add") 
         public String getCatAdd(Model model) {
           model.addAttribute("category", new Category());
           return "categoriesAdd";
     }

      @PostMapping("/admin/categories/add") 
          public String saveCategory(@ModelAttribute("category") Category category ) {
            categoryService.addCategory(category);
             return "redirect:/admin/categories";
      }

      @GetMapping("/admin/categories/delete/{id}")
      public String deleteCat(@PathVariable Long id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
      }

     @GetMapping("/admin/categories/update/{id}")
     public String updateCat(@PathVariable Long id, Model model) {
         Optional <Category> category = categoryService.getCategoryById(id);
         if(category.isPresent()){
             model.addAttribute("category", category.get());
             return "categoriesAdd";
         }else {
         return "404";
        } 
     }



    // Products 

      @GetMapping("/admin/products") 
          public String products(Model model) {
              model.addAttribute("products", productService.getAllProduct());
              return "products";
      }

      @GetMapping("/admin/products/add") 
          public String productAdd(Model model) {
              model.addAttribute("productDTO", new ProductDTO());
              model.addAttribute("categories", categoryService.getAllCategory());
              return "productsAdd";
      }

       @PostMapping("/admin/products/add")
       public String postProductAdd(@ModelAttribute("productDTO")
                                     ProductDTO productDTO,
                                     @RequestParam("productImage") MultipartFile file,
                                     @RequestParam("image") String image) throws IOException {

                                         Product product = new Product();
                                         product.setName(productDTO.getName());
                                         product.setPrice(productDTO.getPrice());
                                         product.setDescription(productDTO.getDescription());
                                         product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
                                         String imageUUID;
                                         if (!file.isEmpty()) {
                                             imageUUID = file.getOriginalFilename();
                                             Path fileNameAndPath = Paths.get(uploadDir,  imageUUID);
                                             Files.write(fileNameAndPath, file.getBytes());
                                         } else {
                                             imageUUID = image;
                                         }
                                         product.setImage(imageUUID);
                                         productService.addProduct(product);
                                         return "redirect:/admin/products";
                                 }

    @GetMapping("/admin/product/update/{id}")
    public String updateProductGet(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImage(product.getImage());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);
        return "productsAdd";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    // Manufacturer

    @GetMapping("/admin/manufacturer")
    public String getMaker(Model model) {
        model.addAttribute("manufacturer", manufacturerService.getAllManufacturer());
        return "manufacturer";
    }
    @GetMapping("/admin/manufacturer/add")
    public String getMakerAdd(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturerAdd";
    }
    @PostMapping("/admin/manufacturer/add")
    public String postMakerAdd(@ModelAttribute("maker") Manufacturer manufacturer) {
        manufacturerService.addManufacturer(manufacturer);
        return "redirect:/admin/manufacturer";
    }
    @GetMapping("/admin/manufacturer/delete/{id}")
    public String deleteManufacturer(@PathVariable Long id) {
        manufacturerService.removeManufacturerById(id);
        return "redirect:/admin/manufacturer";
    }
    @GetMapping("/admin/manufacturer/update/{id}")
    public String updateMaker(@PathVariable Long id, Model model) {
        Optional<Manufacturer> manufacturer = manufacturerService.getManufacturerById(id);
        if (manufacturer.isPresent()) {
            model.addAttribute("manufacturer", manufacturer.get());
            return "manufacturerAdd";
        } else {
        return "404";
        }
    }



    // Supplier
    @GetMapping("/admin/supplier")
    public String getEditor(Model model) {
        model.addAttribute("supplier",supplierService.getAllSupplier());
        return "supplier";
    }

    @GetMapping("/admin/supplier/add")
    public String getSupplierAdd(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplierAdd";
    }
    @PostMapping("/admin/supplier/add")
    public String postSupplierAdd(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.addSupplier(supplier);
        return "redirect:/admin/supplier";

    }

    @GetMapping("/admin/supplier/delete/{id}")
    public String deleteEditor(@PathVariable Long id) {
        supplierService.removeSupplierById(id);
        return "redirect:/admin/supplier";
    }
    
     @GetMapping("/admin/supplier/update/{id}")
     public String updateSupplier(@PathVariable Long id, Model model) {
         Optional<Supplier> supplier = supplierService.getSupplierById(id);
         if (supplier.isPresent()) {
             model.addAttribute("supplier", supplier.get());
             return "supplierAdd";
         } else {
         return "404";
         }
     }   

}