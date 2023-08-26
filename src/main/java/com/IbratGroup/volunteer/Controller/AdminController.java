package com.IbratGroup.volunteer.Controller;


import com.IbratGroup.volunteer.Entity.Admin;
import com.IbratGroup.volunteer.Exception.ResourceNotFoundException;
import com.IbratGroup.volunteer.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
