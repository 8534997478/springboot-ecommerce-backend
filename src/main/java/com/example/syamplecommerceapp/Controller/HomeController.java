package com.example.syamplecommerceapp.Controller;

import com.example.syamplecommerceapp.Service.ProductService;
import com.example.syamplecommerceapp.entity.Admin;
import com.example.syamplecommerceapp.entity.Massage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping({"/", "/home"})
    public String homePage() {
        return "HomePage";
    }

    @GetMapping("/products") // Missing annotation added
    public String productPage(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "ProductPage";
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("massage", new Massage());
        return "ContactPage";
    }

    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "AboutUs";
    }

    @GetMapping("/login") // Missing annotation added
    public String login(Model model) {
        model.addAttribute("admin", new Admin());
        return "Login";
    }
}




