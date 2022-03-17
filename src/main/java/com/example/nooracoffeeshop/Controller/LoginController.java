 /*
 package com.example.nooracoffeeshop.controller;



 import com.example.nooracoffeeshop.model.Role;
 import com.example.nooracoffeeshop.model.User;
 import com.example.nooracoffeeshop.repository.RoleRepository;
 import com.example.nooracoffeeshop.repository.UserRepository;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.PostMapping;

 @Controller
 public interface LoginController {

     @Autowired 
     private BCryptPasswordEncoder bCryptPasswordEncoder;
     @Autowired 
     UserRepository userRepository;

     @Autowired 
     RoleRepository roleRepository;

   
    
     @GetMapping("/register")
     public String registerGet() {
         return "register";
     }
    
     @PostMapping("/register")
     public String rgisterPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
         String password = user.getPassword();
         user.setPassword(bCryptPasswordEncoder.encode(password));
         List<Role> roles = new ArrayList<>();
             roles.add(roleRepository.findById(2).get());
        
         user.setRoles(roles);
         userRepository.save(user);
         request.login(user.getEmail(), password);
         return "redirect:/";
     }
     @GetMapping("/password")
     public String passwordGet() {
         return "password";
     }
    
}

