package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.Course;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseResponseDto extends CommonApiResponse {

    private List<Course> courses = new ArrayList<>();

    private Course course;

    private String isCoursePurchased;

}