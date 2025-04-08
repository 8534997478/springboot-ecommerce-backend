package com.example.syamplecommerceapp.Service;

import com.example.syamplecommerceapp.entity.products;
import com.example.syamplecommerceapp.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    // Get all products
    public List<products> getAllProducts() {
        return productRepo.findAll();
    }

    public List<products> searchProducts(String keyword) {
        return productRepo.findByNameContainingIgnoreCase(keyword);
    }


    // Get product by ID
    public products getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("product with id" + id + "not found"));

    }

    // Add new product
    public void saveProduct(products product) {
         productRepo.save(product);
    }


    // Update an existing product
    public void updateProduct(products products) {
        productRepo.findById(products.getId()).orElseThrow(() -> new RuntimeException("product with id" + products.getId() + "not found"));
        productRepo.save(products);
    }

    // Delete product by ID
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }


    public products findProductByName(String name){
        return productRepo.findByName(name);
    }
}
