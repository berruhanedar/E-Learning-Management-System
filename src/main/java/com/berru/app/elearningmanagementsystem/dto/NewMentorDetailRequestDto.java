package com.berru.app.elearningmanagementsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewMentorDetailRequestDto {

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age cannot exceed 120")
    private int age;

    @DecimalMin(value = "0.0", inclusive = false, message = "Experience must be greater than 0")
    private double experience;

    @NotBlank(message = "Biography cannot be blank")
    @Size(max = 2000, message = "Biography must be up to 2000 characters")
    private String biography;

    @NotBlank(message = "Job title cannot be blank")
    @Size(max = 100, message = "Job title can be up to 100 characters")
    private String jobTitle;

    @Size(max = 500, message = "Qualification can be up to 500 characters")
    private String qualification;

    @NotNull(message = "Profile image cannot be null")
    private MultipartFile profileImageUrl;

    @Min(value = 1, message = "Mentor ID must be provided")
    private int mentorId;
}
