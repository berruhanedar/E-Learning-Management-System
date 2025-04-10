package com.berru.app.elearningmanagementsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewCourseSectionTopicRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotBlank(message = "Order number cannot be blank")
    @Pattern(regexp = "^\\d+$", message = "Order number must be a valid number")
    private String orderNumber;

    @NotNull(message = "Video file cannot be null")
    private MultipartFile videoFile;

    @NotNull(message = "Section ID cannot be null")
    @Min(value = 1, message = "Section ID must be a positive integer")
    private int sectionId;
}
