package com.IbratGroup.volunteer.Repository;

import com.IbratGroup.volunteer.Entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    Optional<Volunteer> findByVolunteerId(String volunteerId);
    // Kerakli so'rovlar uchun metodlar
}