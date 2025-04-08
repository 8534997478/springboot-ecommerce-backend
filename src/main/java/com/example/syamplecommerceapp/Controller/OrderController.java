package com.example.syamplecommerceapp.Controller;

import com.example.syamplecommerceapp.Service.AdminService;
import com.example.syamplecommerceapp.Service.OrderService;
import com.example.syamplecommerceapp.Service.ProductService;
import com.example.syamplecommerceapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class OrderController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;




}
