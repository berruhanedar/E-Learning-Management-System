package com.berru.app.elearningmanagementsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewCourseSectionTopicRequest {

    @NotNull(message = "Section ID cannot be null")
    private int sectionId;

    @NotNull(message = "Serial number cannot be null")
    @Pattern(regexp = "[A-Za-z0-9]{3,}", message = "Serial number must be alphanumeric and at least 3 characters long")
    private String srNo;

    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "[A-Za-z\\s]{3,}", message = "Name must be at least 3 characters long and contain only letters and spaces")
    private String name;

    @NotNull(message = "Description cannot be null")
    @Size(min = 10, message = "Description must be at least 10 characters long")
    private String description;

    @NotNull(message = "Video cannot be null")
    private MultipartFile video;
}
