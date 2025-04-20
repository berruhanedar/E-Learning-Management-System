package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.Course;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class NewCourseRequestDto {

    private int id;

    private int mentorId;

    private int categoryId;

    private String name;

    private String description;

    private String type;

    private BigDecimal fee;

    private int discountInPercent;

    private String authorCourseNote;

    private String specialNote;

    private String prerequisite;

    private MultipartFile notesFileName;

    private MultipartFile thumbnail;

//    public static Course toEntity(NewCourseRequestDto dto) {
//        Course course = new Course();
//        BeanUtils.copyProperties(dto, course, "id", "mentorId", "categoryId", "notesFileName", "thumbnail");
//        return course;
//    }

}