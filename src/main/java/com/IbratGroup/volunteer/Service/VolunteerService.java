package com.IbratGroup.volunteer.Service;


import com.IbratGroup.volunteer.Entity.Volunteer;
import com.IbratGroup.volunteer.Repository.VolunteerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Map<String, Object>> getSubscriptionStatisticsWithSubscribers() {
        List<Volunteer> allVolunteers = volunteerRepository.findAll();
        int totalVolunteers = allVolunteers.size();

        Map<String, List<Volunteer>> subscribersByType = new HashMap<>();
        String[] volunteerTypes = {"Eko volontyor", "Agro volontyor", "Inkluziv volontyor", "Favqullotda volontyor", "Ta’lim volontyor", "Ijtimoiy volontyor", "Sport volontyor", "Zoo volontyor", "Boshqa"};

        for (String type : volunteerTypes) {
            List<Volunteer> subscribers = allVolunteers.stream()
                    .filter(v -> v.getChooseTypeVolunteer().equals(type))
                    .collect(Collectors.toList());
            subscribersByType.put(type, subscribers);
        }

        List<Map<String, Object>> subscriptionStatisticsWithSubscribers = new ArrayList<>();

        for (String type : volunteerTypes) {
            Map<String, Object> subscriptionInfo = new HashMap<>();
            subscriptionInfo.put("chooseTypeVolunteer", type);

            long count = subscribersByType.get(type).size();
            double percentage = (count / (double) totalVolunteers) * 100;
            subscriptionInfo.put("percentage", percentage);

            List<Map<String, Object>> subscriberList = new ArrayList<>();
            for (Volunteer subscriber : subscribersByType.get(type)) {
                Map<String, Object> subscriberInfo = new HashMap<>();
                subscriberInfo.put("id", subscriber.getId());
                subscriberInfo.put("volunteerId", subscriber.getVolunteerId());
                subscriberInfo.put("firstName", subscriber.getFirstName());
                subscriberInfo.put("lastName", subscriber.getLastName());
                subscriberInfo.put("imagePath", subscriber.getImagePath());
                subscriberInfo.put("birthDate", subscriber.getBirthDate());
                subscriberInfo.put("workAndStudent", subscriber.getWorkAndStudent());
                subscriberInfo.put("email", subscriber.getEmail());
                subscriberInfo.put("place", subscriber.getPlace());
                subscriberInfo.put("phoneNumber", subscriber.getPhoneNumber());
                subscriberInfo.put("aboutMe", subscriber.getAboutMe());
                subscriberInfo.put("chooseTypeVolunteer", subscriber.getChooseTypeVolunteer());
                subscriberInfo.put("howHelp", subscriber.getHowHelp());
                subscriberInfo.put("language", subscriber.getLanguage());
                subscriberInfo.put("score", subscriber.getScore());
                subscriberList.add(subscriberInfo);
            }
            subscriptionInfo.put("subscribers", subscriberList);

            subscriptionStatisticsWithSubscribers.add(subscriptionInfo);
        }

        // Nechta user qaysi turga obuna bo'lganini qo'shish
        Map<String, Long> subscriptionCounts = new HashMap<>();
        for (String type : volunteerTypes) {
            long count = subscribersByType.get(type).size();
            subscriptionCounts.put(type, count);
        }

        for (Map<String, Object> subscription : subscriptionStatisticsWithSubscribers) {
            String type = (String) subscription.get("chooseTypeVolunteer");
            long count = subscriptionCounts.get(type);
            subscription.put("subscriberCount", count);
        }

        return subscriptionStatisticsWithSubscribers;
    }
}
