package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.Address;
import com.berru.app.elearningmanagementsystem.entity.MentorDetail;
import com.berru.app.elearningmanagementsystem.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserDto {

    private int id;

    private String firstName;

    private String lastName;

    private String emailId;

    private String phoneNo;

    private String role;

    private Address address;

    private MentorDetail mentorDetail;

    private String status;

    public static UserDto toUserDtoEntity(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}
