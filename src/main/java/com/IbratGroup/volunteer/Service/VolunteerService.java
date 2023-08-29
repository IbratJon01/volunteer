package com.IbratGroup.volunteer.Service;


import com.IbratGroup.volunteer.Entity.Volunteer;
import com.IbratGroup.volunteer.Repository.VolunteerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public List<Volunteer> searchUsersByUsername(String userName) {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        List<Volunteer> matchingUsers = new ArrayList<>();
        for (Volunteer volunteer : volunteers) {
            if (volunteer.getFirstName().toLowerCase().contains(userName.toLowerCase())) {
                matchingUsers.add(volunteer);
            }
        }
        return matchingUsers;
    }
}
