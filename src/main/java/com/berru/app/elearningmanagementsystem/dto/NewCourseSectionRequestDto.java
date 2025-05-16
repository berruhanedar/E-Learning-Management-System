package com.berru.app.elearningmanagementsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class NewCourseSectionRequestDto {

    @NotNull(message = "Course ID cannot be null")
    private int courseId;

    @NotNull(message = "Serial number cannot be null")
    private String srNo;

    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "[A-Za-z\\s]{3,}", message = "Name must be at least 3 characters long and contain only letters and spaces")
    private String name;

    @NotNull(message = "Description cannot be null")
    @Size(min = 10, message = "Description must be at least 10 characters long")
    private String description;
}
