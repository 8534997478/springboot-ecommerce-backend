package com.example.syamplecommerceapp.Controller;

import com.example.syamplecommerceapp.Service.ProductService;
import com.example.syamplecommerceapp.entity.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product/addProducts")
    public String addProduct(){
        return "product/addProducts";
    }
    @PostMapping("/admin/addProduct")
    public String addProduct(@ModelAttribute products product) throws IOException {
        MultipartFile imageFile = product.getImageFile();

        if (!imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            String uploadDir = "src/main/resources/static/images/";

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            product.setImagePath(fileName);
        }

        productService.saveProduct(product);
        return "redirect:/admin/home";
    }

    @GetMapping("/update/product/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        products product = productService.getProductById(id);
        model.addAttribute("product", product);

        return "Product/UpdateProducts";
    }


    @PostMapping("/update/product/{id}")
    public String updateProduct(products products){
        productService.updateProduct(products);
        return "redirect:/admin/home";
    }
    @GetMapping("/delete/product/{id}")
    public String deleteProduct(@PathVariable Long id,Model model){
        productService.deleteProduct(id);
        return "redirect:/admin/home";
    }




}
