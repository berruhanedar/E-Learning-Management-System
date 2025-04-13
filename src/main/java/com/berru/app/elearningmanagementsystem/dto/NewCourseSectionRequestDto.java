package com.berru.app.elearningmanagementsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewCourseSectionRequestDto {

    private int courseId;

    private String srNo;

    private String name;

    private String description;


}
