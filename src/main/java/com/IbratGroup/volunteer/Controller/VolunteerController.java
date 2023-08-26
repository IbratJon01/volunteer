package com.IbratGroup.volunteer.Controller;

import com.IbratGroup.volunteer.Entity.Volunteer;
import com.IbratGroup.volunteer.Exception.ResourceNotFoundException;
import com.IbratGroup.volunteer.Repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {
    @Autowired
    private VolunteerRepository volunteerRepository;

    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Volunteer getVolunteerById(@PathVariable Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", id));
    }

    @PostMapping
    public Volunteer createVolunteer(@RequestBody Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @PutMapping("/{id}")
    public Volunteer updateVolunteer(@PathVariable Long id, @RequestBody Volunteer volunteerDetails) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", id));

        // Ma'lumotlarni o'zgartirish
        volunteer.setFirstName(volunteerDetails.getFirstName());
        volunteer.setLastName(volunteerDetails.getLastName());
        volunteer.setBirthDate(volunteerDetails.getBirthDate());
        // Qolgan ma'lumotlarni ham o'zgartirish

        return volunteerRepository.save(volunteer);
    }

    @DeleteMapping("/{id}")
    public void deleteVolunteer(@PathVariable Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", id));

        volunteerRepository.delete(volunteer);
    }
}
