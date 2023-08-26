package com.IbratGroup.volunteer.Repository;

import com.IbratGroup.volunteer.Entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    // Kerakli so'rovlar uchun metodlar
}