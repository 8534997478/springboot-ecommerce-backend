package com.example.syamplecommerceapp.repo;

import com.example.syamplecommerceapp.entity.User;
import com.example.syamplecommerceapp.entity.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<order, Long> {
    // Custom queries if needed
    public List<order> findByUser(User user);
}
