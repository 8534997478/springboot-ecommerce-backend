package com.example.syamplecommerceapp.repo;

import com.example.syamplecommerceapp.entity.products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<products, Long> {

    // Custom query methods (if needed)
    public products findByName(String name);
    List<products> findByNameContainingIgnoreCase(String keyword);

}
