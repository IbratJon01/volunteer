package com.IbratGroup.volunteer.Controller;


import com.IbratGroup.volunteer.Entity.Admin;
import com.IbratGroup.volunteer.Entity.Volunteer;
import com.IbratGroup.volunteer.Exception.ResourceNotFoundException;
import com.IbratGroup.volunteer.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/{id}")
    public Admin getVolunteerById(@PathVariable Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", id));
    }
    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody Admin newAdmin) {
        Optional<Admin> existingVolunteer = adminRepository.findByAdminId(newAdmin.getAdminId());
// @Column(unique = true) // Unique Volunteer ID Validation
        if (existingVolunteer.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ma'lumot allaqachon mavjud. Yangilash amalga oshirildi.");
        } else {
            Admin createdAdmin = adminRepository.save(newAdmin);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
        }
    }

}