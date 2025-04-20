package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.Address;
import com.berru.app.elearningmanagementsystem.entity.User;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class RegisterUserRequestDto {

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String emailId;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNo;

    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "^(admin|user|mentor)$", message = "Role must be one of the following: admin, user, mentor")
    private String role; // Assuming predefined roles (admin, user, mentor)

    @NotBlank(message = "Street cannot be blank")
    @Size(min = 3, max = 100, message = "Street must be between 3 and 100 characters")
    private String street;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    private String city;

    @NotNull(message = "Postal code cannot be null")
    @Pattern(regexp = "^[0-9]{5,6}$", message = "Postal code must be 5 or 6 digits")
    private int postalcode;

//    public static User toUserEntity(RegisterUserRequestDto registerUserRequestDto) {
//        User user = new User();
//        BeanUtils.copyProperties(registerUserRequestDto, user);
//        return user;
//    }
}
