package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Category;
import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.entity.User;
import com.berru.app.elearningmanagementsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course add(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }

    public Course getById(int id) {

        Optional<Course> optional = this.courseRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }

    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public List<Course> getByStatus(String status) {
        return courseRepository.findByStatusOrderByIdDesc(status);
    }

    public List<Course> getByMentorAndStatus(User mentor, String status) {
        return courseRepository.findByMentorAndStatusOrderByIdDesc(mentor, status);
    }

    public List<Course> getByMentor(User mentor) {
        return courseRepository.findByMentorOrderByIdDesc(mentor);
    }

    public List<Course> getByCategoryAndStatus(Category category, String status) {
        return courseRepository.findByCategoryAndStatusOrderByIdDesc(category, status);
    }

    public List<Course> getByNameAndStatus(String name, String status) {
        return courseRepository.findByStatusAndNameContainingIgnoreCaseOrderByIdDesc(status, name);
    }

    public List<Course> updateAll(List<Course> courses) {
        return courseRepository.saveAll(courses);
    }

    public Long getCountByMentorAndStatus(User mentor, String status) {
        return courseRepository.countByMentorAndStatus(mentor, status);
    }
}