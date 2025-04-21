package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.entity.CourseSection;
import com.berru.app.elearningmanagementsystem.repository.CourseSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseSectionService {

    @Autowired
    private CourseSectionRepository courseSectionRepository;

    public CourseSection add(CourseSection section) {
        return courseSectionRepository.save(section);
    }

    public List<CourseSection> getByCourse(Course course) {
        return courseSectionRepository.findByCourse(course);
    }

    public CourseSection getById(int section) {

        Optional<CourseSection> optional = this.courseSectionRepository.findById(section);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }
}