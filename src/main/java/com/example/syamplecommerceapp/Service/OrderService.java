package com.example.syamplecommerceapp.Service;

import com.example.syamplecommerceapp.entity.User;
import com.example.syamplecommerceapp.entity.order;
import com.example.syamplecommerceapp.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public List<order> getAllOrders() {
        return orderRepo.findAll();
    }

    public order getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with ID " + id + " not found"));
    }

    public order saveOrder(order order) {
        return orderRepo.save(order);
    }

    public void updateOrder(order order) {
        orderRepo.findById(order.getId())
                .orElseThrow(() -> new RuntimeException("Order with ID " + order.getId() + " not found"));
        orderRepo.save(order);
    }

    public void deleteOrder(Long id) {
        if (!orderRepo.existsById(id)) {
            throw new RuntimeException("Order with ID " + id + " not found");
        }
        orderRepo.deleteById(id);
    }

    public List<order> findOrderByUser(User user) {

        return orderRepo.findByUser(user);
    }
}
