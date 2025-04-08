package com.example.syamplecommerceapp.Controller;

import com.example.syamplecommerceapp.Service.ContactService;
import com.example.syamplecommerceapp.entity.Massage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Handle Form Submission
    @PostMapping("/submitContact")
    public String submitContactForm(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("message") String message,
            Model model) {

        // Create and save the massage
        Massage massage = new Massage(name, email, message);
        contactService.saveMassage(massage);  // ✅ Corrected method call

        // Add confirmation message to the model
        model.addAttribute("successMessage", "Your message has been sent successfully!");

        return "ContactPage"; // Redirect back to contact page
    }
}
