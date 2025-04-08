package com.example.syamplecommerceapp.repo;

import com.example.syamplecommerceapp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    // Custom query methods (optional)
    Admin findByEmail(String email);
}
