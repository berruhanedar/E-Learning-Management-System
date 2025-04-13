package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.MentorDetail;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewMentorDetailRequestDto {

    private int id;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age cannot exceed 120")
    private int age;

    private String bio;

    private String highestQualification;

    private String profession;

    private double experience;

    private MultipartFile profilePic;

    private int mentorId;

    public static MentorDetail toEntity(NewMentorDetailRequestDto newMentorDetailRequestDto) {
        MentorDetail mentorDetail = new MentorDetail();
        BeanUtils.copyProperties(newMentorDetailRequestDto, mentorDetail, "profilePic", "mentorId", "id");
        return mentorDetail;
    }
}
