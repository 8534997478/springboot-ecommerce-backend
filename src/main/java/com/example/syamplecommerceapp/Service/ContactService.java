package com.example.syamplecommerceapp.Service;

import com.example.syamplecommerceapp.entity.Massage;
import com.example.syamplecommerceapp.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepo contactRepo;

    @Autowired
    public ContactService(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    // Save a new massage
    public Massage saveMassage(Massage massage) {
        return contactRepo.save(massage);
    }

    // Get all massages (for admin)
    public List<Massage> getAllMassages() {
        return contactRepo.findAll();
    }

    // Get a single massage by ID
    public Massage getMassageById(Long id) {
        return contactRepo.findById(id).orElse(null);
    }

    // Delete a massage by ID
    public void deleteMassage(Long id) {
        contactRepo.deleteById(id);
    }
}
