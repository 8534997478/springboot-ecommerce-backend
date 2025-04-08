package com.example.syamplecommerceapp.repo;


import com.example.syamplecommerceapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    // Custom query methods (if needed)
    User findByEmail(String email);


}
