package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.MentorDetail;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewMentorDetailRequestDto {

    private int id;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age cannot exceed 120")
    private int age;

    @NotNull(message = "Bio cannot be null")
    @Size(min = 10, message = "Bio must be at least 10 characters long")
    private String bio;

    @NotNull(message = "Highest qualification cannot be null")
    @Pattern(regexp = "[A-Za-z\\s]{3,}", message = "Highest qualification must be at least 3 characters long and contain only letters and spaces")
    private String highestQualification;

    @NotNull(message = "Profession cannot be null")
    @Pattern(regexp = "[A-Za-z\\s]{3,}", message = "Profession must be at least 3 characters long and contain only letters and spaces")
    private String profession;

    @Positive(message = "Experience must be a positive number")
    private double experience;

    @NotNull(message = "Profile picture cannot be null")
    private MultipartFile profilePic;

    @NotNull(message = "Mentor ID cannot be null")
    @Positive(message = "Mentor ID must be a positive number")
    private int mentorId;

//    public static MentorDetail toEntity(NewMentorDetailRequestDto newMentorDetailRequestDto) {
//        MentorDetail mentorDetail = new MentorDetail();
//        BeanUtils.copyProperties(newMentorDetailRequestDto, mentorDetail, "profilePic", "mentorId", "id");
//        return mentorDetail;
//    }
}
