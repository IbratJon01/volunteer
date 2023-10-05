package com.IbratGroup.volunteer.Controller;

import com.IbratGroup.volunteer.Entity.Volunteer;
import com.IbratGroup.volunteer.Exception.ResourceNotFoundException;
import com.IbratGroup.volunteer.Repository.VolunteerRepository;
import com.IbratGroup.volunteer.Service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {
    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public Volunteer getVolunteerById(@PathVariable Long id) {
//        return volunteerRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", id));
//    }
    @GetMapping("/{volunteerId}")
    public Volunteer getVolunteerByVolunteerId(@PathVariable String volunteerId) {
        return volunteerRepository.findByVolunteerId(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", volunteerId));
    }

    @PostMapping
    public ResponseEntity<?> createVolunteer(@RequestBody Volunteer newVolunteer) {
        Optional<Volunteer> existingVolunteer = volunteerRepository.findByVolunteerId(newVolunteer.getVolunteerId());
// @Column(unique = true) // Unique Volunteer ID Validation
        if (existingVolunteer.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ma'lumot allaqachon mavjud. Yangilash amalga oshirildi.");
        } else {
            Volunteer createdVolunteer = volunteerRepository.save(newVolunteer);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVolunteer);
        }
    }

    @GetMapping("/subscription-statistics")
    public ResponseEntity<List<Map<String, Object>>> getSubscriptionStatisticsWithSubscribers() {
        List<Map<String, Object>> statisticsWithSubscribers = volunteerService.getSubscriptionStatisticsWithSubscribers();
        return ResponseEntity.ok(statisticsWithSubscribers);
    }



    @PutMapping("/{id}")
    public Volunteer updateVolunteer(@PathVariable Long id, @RequestBody Volunteer volunteerDetails) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", id));

        // Ma'lumotlarni o'zgartirish
        volunteer.setFirstName(volunteerDetails.getFirstName());
        volunteer.setLastName(volunteerDetails.getLastName());
        volunteer.setAboutMe(volunteerDetails.getAboutMe());
        volunteer.setPhoneNumber(volunteerDetails.getPhoneNumber());
        volunteer.setPlace(volunteerDetails.getPlace());
        volunteer.setHowHelp(volunteerDetails.getHowHelp());
        volunteer.setImagePath(volunteerDetails.getImagePath());
        volunteer.setChooseTypeVolunteer(volunteerDetails.getChooseTypeVolunteer());
        volunteer.setEmail(volunteerDetails.getEmail());
        volunteer.setWorkAndStudent(volunteerDetails.getWorkAndStudent());
        volunteer.setBirthDate(volunteerDetails.getBirthDate());
        volunteer.setLanguage(volunteerDetails.getLanguage());
        volunteer.setScore(volunteerDetails.getScore());
        // Qolgan ma'lumotlarni ham o'zgartirish

        return volunteerRepository.save(volunteer);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Volunteer>> searchUsersByUsername(@RequestParam String userName) {
        List<Volunteer> users = volunteerService.searchUsersByUsername(userName);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteVolunteer(@PathVariable Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", id));

        volunteerRepository.delete(volunteer);
    }
}
