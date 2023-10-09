package com.IbratGroup.volunteer.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String volunteerId;
    private String firstName;
    private String lastName;
    private String imagePath;
    private String birthDate;
    private String workAndStudent;
    private String email;
    private String place;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    private String phoneNumber;
    private String aboutMe;
    private String chooseTypeVolunteer;
    private String howHelp;
    private String language;
    private int score;




}
