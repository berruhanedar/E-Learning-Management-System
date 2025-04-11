package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.entity.CourseSection;

import java.util.List;

public interface CourseSectionService {
    CourseSection add(CourseSection section);

    CourseSection getById(int section);

    List<CourseSection> getByCourse(Course course);
}
