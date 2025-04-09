package com.berru.app.elearningmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String accountStatus;

    private String email;

    private String firstName;

    private String lastName;

    @JsonIgnore
    private String password;

    private String phone;

    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "mentor_detail_id")
    private MentorDetail mentorDetail;

    private String role;
}
