package com.berru.app.elearningmanagementsystem.controller;
import java.io.IOException;

import com.berru.app.elearningmanagementsystem.dto.*;
import com.berru.app.elearningmanagementsystem.facade.CourseFacade;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/course")
@CrossOrigin(origins = "http://localhost:8080")
public class CourseController {

    @Autowired
    private CourseFacade courseFacade;

    @PostMapping("add")
    @Operation(summary = "Api to add the Mentor Course")
    public ResponseEntity<CourseResponseDto> addCourse(NewCourseRequestDto request) {
        return this.courseFacade.addCourse(request);
    }

    @PostMapping("section/add")
    @Operation(summary = "Api to add the course section")
    public ResponseEntity<CourseResponseDto> addCourseSection(@RequestBody NewCourseSectionRequestDto request) {
        return this.courseFacade.addCourseSection(request);
    }

    @PostMapping("section/topic/add")
    @Operation(summary = "Api to add the course section topic")
    public ResponseEntity<CourseResponseDto> addCourseSectionTopic(NewCourseSectionTopicRequest request) {
        return this.courseFacade.addCourseSectionTopic(request);
    }

    @GetMapping("/fetch/course-id")
    @Operation(summary = "Api to fetch course by using course id")
    public ResponseEntity<CourseResponseDto> fetchCourseById(@RequestParam("courseId") Integer courseId,
                                                             @RequestParam("videoShow") String videoShow) {
        return courseFacade.fetchCourseById(courseId, videoShow);
    }

    @GetMapping("/fetch/course-user-id")
    @Operation(summary = "Api to fetch course by using course id and student id")
    public ResponseEntity<CourseResponseDto> fetchCourseById(@RequestParam("courseId") Integer courseId,
                                                             @RequestParam("userId") Integer userId) {
        return courseFacade.fetchCourseByIdAndUserId(courseId, userId);
    }

    @GetMapping("/fetch/status-wise")
    @Operation(summary = "Api to fetch courses by using status")
    public ResponseEntity<CourseResponseDto> fetchCoursesByStatus(@RequestParam("status") String status,
                                                                  @RequestParam("videoShow") String videoShow) {
        return courseFacade.fetchCoursesByStatus(status, videoShow);
    }

    @GetMapping("/fetch/mentor-wise")
    @Operation(summary = "Api to fetch courses by using status")
    public ResponseEntity<CourseResponseDto> fetchCoursesByMentor(@RequestParam("mentorId") Integer mentorId,
                                                                  @RequestParam("status") String status, @RequestParam("videoShow") String videoShow) {
        return courseFacade.fetchCoursesByMentor(mentorId, status, videoShow);
    }

    @GetMapping("/fetch/category-wise")
    @Operation(summary = "Api to fetch courses by using category")
    public ResponseEntity<CourseResponseDto> fetchCoursesByCategory(@RequestParam("categoryId") Integer categoryId,
                                                                    @RequestParam("videoShow") String videoShow) {
        return courseFacade.fetchCoursesByCategory(categoryId, videoShow);
    }

    @GetMapping("/fetch/name-wise")
    @Operation(summary = "Api to fetch courses by using name")
    public ResponseEntity<CourseResponseDto> fetchCoursesByName(@RequestParam("courseName") String courseName) {
        return courseFacade.fetchCoursesByName(courseName);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Api to delete the course")
    public ResponseEntity<CommonApiResponse> deleteCourse(@RequestParam("courseId") Integer courseId) {
        return courseFacade.deleteCourse(courseId);
    }

    @GetMapping(value = "/{courseImageName}", produces = "image/*")
    public void fetchCourseImage(@PathVariable("courseImageName") String courseImageName, HttpServletResponse resp) {
        this.courseFacade.fetchCourseImage(courseImageName, resp);
    }

    @GetMapping(value = "/video/{courseSectionTopicVideoFileName}", produces = "video/*")
    public void fetchCourseTopicVideo(
            @PathVariable("courseSectionTopicVideoFileName") String courseSectionTopicVideoFileName,
            HttpServletResponse resp) {
        this.courseFacade.fetchCourseTopicVideo(courseSectionTopicVideoFileName, resp);
    }

    @GetMapping("notes/{notesFileName}/download")
    @Operation(summary = "Api for downloading the Course Notes")
    public ResponseEntity<Resource> downloadNotes(@PathVariable("notesFileName") String notesFileName,
                                                  HttpServletResponse response) throws DocumentException, IOException {
        return this.courseFacade.downloadNotes(notesFileName, response);
    }

    @GetMapping("/mentor/dashboard")
    @Operation(summary = "Api to fetch mentor dashboard data")
    public ResponseEntity<MentorResponseDto> fetchMentorDashboardData(@RequestParam("mentorId") Integer mentorId) {
        return courseFacade.fetchMentorDashboardData(mentorId);
    }
}