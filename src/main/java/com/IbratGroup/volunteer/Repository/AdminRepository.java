package com.IbratGroup.volunteer.Repository;

import com.IbratGroup.volunteer.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByAdminId(String adminId);
}
