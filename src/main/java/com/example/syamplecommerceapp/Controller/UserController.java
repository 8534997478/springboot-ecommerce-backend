package com.example.syamplecommerceapp.Controller;

import com.example.syamplecommerceapp.Service.AdminService;
import com.example.syamplecommerceapp.Service.OrderService;
import com.example.syamplecommerceapp.Service.ProductService;
import com.example.syamplecommerceapp.Service.UserService;
import com.example.syamplecommerceapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @GetMapping("/verifyCredentials")
//    public String verifyCredentials(@ModelAttribute("user") User user, Model model){
//        if (userService.verifyCredentials(user.getEmail(),user.getPassword())){
//            return "/user/home";
//        }
//        model.addAttribute("error","invalid email and password");
//        return"Login";
//    }

    @GetMapping("/user/addUser")
    public String addUser(){

        return "user/addUser";
    }
    @PostMapping("/add/user")
    public String addUser(User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.saveUser(user);
        return "Login";
    }
    @GetMapping("/update/user/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        model.addAttribute("user",userService.getUserById(id));

        return "user/updateUser";
    }
    @PostMapping("/update/user/{id}")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/admin/home";
    }
    @GetMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/admin/home";  // Redirect after deletion
    }





}
