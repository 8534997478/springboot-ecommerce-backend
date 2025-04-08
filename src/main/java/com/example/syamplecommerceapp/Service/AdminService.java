package com.example.syamplecommerceapp.Service;

import com.example.syamplecommerceapp.entity.Admin;
import com.example.syamplecommerceapp.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    // Get all admins
    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    // Get admin by ID
    public Optional<Admin> getAdminById(Long id) {
        return adminRepo.findById(id);
    }

    // Add new admin
    public Admin saveAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    // Delete admin by ID
    public void deleteAdmin(Long id) {
        adminRepo.deleteById(id);
    }
    public void updateAdmin(Admin admin) {
        adminRepo.findById(admin.getId()).orElseThrow(() -> new RuntimeException("admin with id" + admin.getId() + "not found"));
        adminRepo.save(admin);
    }
    public boolean verifyCredentials(String email, String password){
        Admin admin = adminRepo.findByEmail(email);

        if (admin == null) {
            return false;  // Return false if user is not found
        }

        return admin.getPassword().equals(password); // Compare password safely
    }
}
