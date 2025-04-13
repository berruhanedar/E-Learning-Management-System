package com.berru.app.elearningmanagementsystem.repository;

import com.berru.app.elearningmanagementsystem.entity.Category;
import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByMentorOrderByIdDesc(User mentor);

    List<Course> findByMentorAndStatusOrderByIdDesc(User mentor, String status);

    List<Course> findByCategoryAndStatusOrderByIdDesc(Category category, String status);

    List<Course> findByStatusAndNameContainingIgnoreCaseOrderByIdDesc(String status, String name);

    List<Course> findByStatusOrderByIdDesc(String status);

    Long countByMentorAndStatus(User mentor, String status);

}