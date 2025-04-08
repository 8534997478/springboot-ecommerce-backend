package com.example.syamplecommerceapp.Controller;

import com.example.syamplecommerceapp.Service.*;
import com.example.syamplecommerceapp.entity.Admin;
import com.example.syamplecommerceapp.entity.User;
import com.example.syamplecommerceapp.entity.order;
import com.example.syamplecommerceapp.entity.products;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RazorpayService razorpayService;


    @GetMapping("/admin/verifyCredentials")
    public String verifyCredentials(@ModelAttribute("admin") Admin admin, Model model){
        if (adminService.verifyCredentials(admin.getEmail(),admin.getPassword())){
            model.addAttribute("adminList", adminService.getAllAdmins());
            model.addAttribute("userList", userService.getAllUsers());
            model.addAttribute("productList", productService.getAllProducts());
            model.addAttribute("orderList", orderService.getAllOrders());
            return "admin/home";
        }
        model.addAttribute("error","invalid email and password");
        return"Login";
    }


    // Get all admins

//    public String verifyCredentials(@RequestParam String email,
//                                    @RequestParam String password,
//                                    Model model) {
//
////        if (adminService.verifyCredentials(email, password)) {
////            model.addAttribute("adminList", adminService.getAllAdmins());
////            model.addAttribute("userList", userService.getAllUsers());
////            model.addAttribute("productList", productService.getAllProducts());
////            model.addAttribute("orderList", orderService.getAllOrders());
////            return "admin/home";
////        }
////
////        model.addAttribute("error", "Invalid email or password");
////        return "Login";
////    }

    @GetMapping("/admin/home")
    public String adminHomePage(Model model){
        List<Admin> admins = adminService.getAllAdmins();
        List<User> users = userService.getAllUsers();
        List<order> orders = orderService.getAllOrders();
        List<products> products = productService.getAllProducts();
        System.out.println(admins);



        model.addAttribute("adminList", admins);
        model.addAttribute("userList", users);
        model.addAttribute("orderList", orders);
        model.addAttribute("productList", products);

        return "admin/home";
    }






    // Add a new admin
    @GetMapping("/admin/addAdmin")
    public String createAdmin() {
        return "admin/addAdmin";
    }
    @PostMapping("/add/admin")
    public String createAdmin(Admin admin) {
//
        adminService.saveAdmin(admin);
        return "redirect:/admin/home";
    }

    // Delete an admin by ID
    @GetMapping("/update/admin/{id}")
    public String updateAdmin(@PathVariable Long id, Model model) {
        model.addAttribute("admin",adminService.getAdminById(id));

        return "admin/updateAdmin";
    }
    @PostMapping("/update/admin/{id}")
    public String updateAdmin(@ModelAttribute("admin") Admin admin){
        adminService.updateAdmin(admin);
        return "redirect:/admin/home";
    }

    @GetMapping("/delete/admin/{id}")
    public String deleteAdmin(@PathVariable long id) {
        adminService.deleteAdmin(id);
        return "redirect:/admin/home";  // Redirect to admin home after deletion
    }

    @GetMapping("/user/login")
    public String userLogin(@ModelAttribute User user, Model model, HttpSession session) {
        if (userService.verifyCredentials(user.getEmail(), user.getPassword())) {
            User loggedInUser = userService.findByEmail(user.getEmail());

            // ✅ Save user email in session
            session.setAttribute("loggedInUserEmail", loggedInUser.getEmail());

            List<products> products = productService.getAllProducts();
            model.addAttribute("productList", products);
            model.addAttribute("orderList", orderService.findOrderByUser(loggedInUser));
            return "user/product";
        }
        model.addAttribute("error", "Invalid email or password");
        return "Login";
    }

    @GetMapping("/products/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<products> result = productService.searchProducts(keyword);
        model.addAttribute("productList", result);
        return "user/product"; // replace with the actual template name
    }

    @GetMapping("/user/placeOrder/{id}")
    public String placeOrder(@PathVariable Long id, Model model) {
        products product = productService.getProductById(id);
        model.addAttribute("order", new order()); // 🔥 Required!

        model.addAttribute("product", product);
        return "user/placeOrder"; // create this Thymeleaf page
    }

    // Optional: handle form submission
    @PostMapping("/placeOrder")
    public String confirmOrder(@RequestParam String name,
                               @RequestParam BigDecimal price,
                               @RequestParam Integer quantity,
                               HttpSession session,
                               Model model) throws Exception {

        String email = (String) session.getAttribute("loggedInUserEmail");
        if (email == null) return "redirect:/login";

        User user = userService.findByEmail(email);

        // ✅ Manually create and populate order
        order order = new order();
        order.setUser(user);
        order.setName(name);
        order.setPrice(price);
        order.setQuantity(quantity);
        order.setDate(new Date());

        BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));
        order.setAmount(amount);

        // ✅ Add it to model (for paymentPage.html to use)
        model.addAttribute("order", order);

        // ✅ Save in session to retrieve later on payment success
        session.setAttribute("pendingOrder", order);

        String receipt = "order_rcptid_" + System.currentTimeMillis();
        String razorpayOrder = razorpayService.createOrder(amount.intValue(), "INR", receipt);

        model.addAttribute("razorpayOrder", razorpayOrder);
        model.addAttribute("apiKey", "rzp_test_15dGbJgD4ajD6F");

        return "user/paymentPage";
    }


    @GetMapping("/payment/success")
    public String paymentSuccess(@RequestParam("paymentId") String paymentId, HttpSession session) {
        order pendingOrder = (order) session.getAttribute("pendingOrder");
        if (pendingOrder == null) return "redirect:/products";

        pendingOrder.setPaymentId(paymentId);
        orderService.saveOrder(pendingOrder);

        session.removeAttribute("pendingOrder");

        return "redirect:/products";  // or product list page
    }


    @GetMapping("/delete/order/{id}")
    public String deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
        return "redirect:/admin/home";  // Redirect to admin home after deletion
    }

}
