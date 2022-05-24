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
              model.addAttribute("suppliers", supplierService.getAllSupplier());
              model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
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
                                         product.setSupplier(supplierService.getSupplierById(productDTO.getSupplierId()).get());
                                        //  product.setManufacturer(manufacturerService.getManufacturerById(productDTO.getManufacturerId()).get());
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

    @GetMapping("/admin/products/update/{id}")
    public String updateProductGet(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setManufacturerId(product.getManufacturer().getId());
        productDTO.setSupplierId(product.getSupplier().getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImage(product.getImage());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("suppliers", supplierService.getAllSupplier());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
        return "productsAdd";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    // Manufacturer

    @GetMapping("/admin/manufacturers")
    public String getManufacturer(Model model) {
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
        return "manufacturers";
    }
    @GetMapping("/admin/manufacturers/add")
    public String getManufacturerAdd(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturersAdd";
    }
    @PostMapping("/admin/manufacturers/add")
    public String postMakerAdd(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
        manufacturerService.addManufacturer(manufacturer);
        return "redirect:/admin/manufacturers";
    }
    @GetMapping("/admin/manufacturers/delete/{id}")
    public String deleteManufacturer(@PathVariable Long id) {
        manufacturerService.removeManufacturerById(id);
        return "redirect:/admin/manufacturers";
    }
    @GetMapping("/admin/manufacturers/update/{id}")
    public String updateManufacturer(@PathVariable Long id, Model model) {
        Optional<Manufacturer> manufacturer = manufacturerService.getManufacturerById(id);
        if (manufacturer.isPresent()) {
            model.addAttribute("manufacturer", manufacturer.get());
            return "manufacturersAdd";
        } else {
        return "404";
        }
    }



    // Supplier
    @GetMapping("/admin/suppliers")
    public String getEditor(Model model) {
        model.addAttribute("suppliers",supplierService.getAllSupplier());
        return "suppliers";
    }

    @GetMapping("/admin/suppliers/add")
    public String getSupplierAdd(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliersAdd";
    }
    @PostMapping("/admin/suppliers/add")
    public String postSupplierAdd(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.addSupplier(supplier);
        return "redirect:/admin/suppliers";

    }

    //  @PostMapping("/admin/suppliers/add")
    // public String postSupplierAdd(@RequestParam String name, 
    //                                 @RequestParam String contactPerson,
    //                                 @RequestParam String contactPersonEmail
    //                                 ) {
    //     supplierService.addSupplier(name, contactPerson, contactPersonEmail);
    //     return "redirect:/admin/suppliers";

    // }

    @GetMapping("/admin/suppliers/delete/{id}")
    public String deleteEditor(@PathVariable Long id) {
        supplierService.removeSupplierById(id);
        return "redirect:/admin/suppliers";
    }
    
     @GetMapping("/admin/suppliers/update/{id}")
     public String updateSupplier(@PathVariable Long id, Model model) {
         Optional<Supplier> supplier = supplierService.getSupplierById(id);
         if (supplier.isPresent()) {
             model.addAttribute("supplier", supplier.get());
             return "suppliersAdd";
         } else {
         return "404";
         }
     }   

}