package com.example.syamplecommerceapp.Service;

import com.example.syamplecommerceapp.entity.User;
import com.example.syamplecommerceapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    // Get all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    // Add new user
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public void updateUser(User user) {
        userRepo.findById(user.getId()).orElseThrow(() -> new RuntimeException("user with id" + user.getId() + "not found"));
        userRepo.save(user);
    }

    // Delete user by ID
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    // Verify User Credentials
    // Verify User Credentials
    public boolean verifyCredentials(String email, String password) {
        User user = userRepo.findByEmail(email);

        if (user == null) {
            return false;  // Return false if user is not found
        }

        return user.getPassword().equals(password); // Compare password safely
    }


}
