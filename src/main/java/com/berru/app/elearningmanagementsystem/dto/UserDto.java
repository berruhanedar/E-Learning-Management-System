package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.Address;
import com.berru.app.elearningmanagementsystem.entity.MentorDetail;
import lombok.Data;

@Data
public class UserDto {

    private int id;

    private Address address;

    private String accountStatus;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private MentorDetail mentorDetail;

    private String role;
}
