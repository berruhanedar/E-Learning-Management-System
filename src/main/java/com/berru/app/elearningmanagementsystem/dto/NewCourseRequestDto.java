package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.Category;
import com.berru.app.elearningmanagementsystem.entity.CourseSection;
import com.berru.app.elearningmanagementsystem.entity.User;
import com.berru.app.elearningmanagementsystem.enums.CourseStatus;
import com.berru.app.elearningmanagementsystem.enums.CourseTypeStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class NewCourseRequestDto {

    @NotNull(message = "Created date cannot be null")
    private LocalDateTime createdAt;

    @NotBlank(message = "Course name cannot be blank")
    @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @Size(max = 1000, message = "Author note can be up to 1000 characters")
    private String authorNote;

    @Size(max = 500, message = "Prerequisite can be up to 500 characters")
    private String prerequisite;

    @Size(max = 255, message = "Notes file name can be up to 255 characters")
    private String notesFileName;

    @Size(max = 500, message = "Special note can be up to 500 characters")
    private String specialNote;

    @Size(max = 255, message = "Thumbnail URL can be up to 255 characters")
    private String thumbnail;

    @Min(value = 0, message = "Discount percent must be at least 0")
    @Max(value = 100, message = "Discount percent cannot exceed 100")
    private int discountPercent;

    @NotNull(message = "Fee cannot be null")
    @Digits(integer = 10, fraction = 2, message = "Fee must be a valid monetary amount")
    private BigDecimal fee;

    @NotNull(message = "Category must be provided")
    private Category categoryId;

    @NotNull(message = "Mentor must be provided")
    private User mentorId;

    private List<CourseSection> sections = new ArrayList<>();

    @NotNull(message = "Course status must be provided")
    private CourseStatus status;

    @NotNull(message = "Course type must be provided")
    private CourseTypeStatus type;
}
