package com.berru.app.elearningmanagementsystem.facade;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.berru.app.elearningmanagementsystem.dto.*;
import com.berru.app.elearningmanagementsystem.entity.*;
import com.berru.app.elearningmanagementsystem.enums.action.CoursePurchased;
import com.berru.app.elearningmanagementsystem.enums.role.UserRole;
import com.berru.app.elearningmanagementsystem.enums.status.ActiveStatus;
import com.berru.app.elearningmanagementsystem.enums.type.CourseTopicVideoShow;
import com.berru.app.elearningmanagementsystem.enums.type.CourseType;
import com.berru.app.elearningmanagementsystem.mapper.CourseMapper;
import com.berru.app.elearningmanagementsystem.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CourseFacade {
    private final Logger LOG = LoggerFactory.getLogger(CourseFacade.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseSectionService courseSectionService;

    @Autowired
    private CourseSectionTopicService courseSectionTopicService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CourseMapper courseMapper;

    public ResponseEntity<CourseResponseDto> addCourse(NewCourseRequestDto request) {

        LOG.info("Received request for adding the mentor course");

        CourseResponseDto apiResponse = new CourseResponseDto();

        if (request == null) {
            apiResponse.setResponseMessage("Missing request body");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        if (request.getCategoryId() == 0 || request.getDescription() == null || request.getMentorId() == 0
                || request.getName() == null || request.getNotesFileName() == null || request.getType() == null
                || request.getThumbnail() == null) {
            apiResponse.setResponseMessage("Missing input");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        String addedDateTime = String
                .valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        Category category = this.categoryService.getCategoryById(request.getCategoryId());

        if (category == null) {
            apiResponse.setResponseMessage("Category not found");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        User mentor = this.userService.getUserById(request.getMentorId());

        if (mentor == null) {
            apiResponse.setResponseMessage("Mentor not found");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        Course course = courseMapper.toEntity(request);

        String courseNote = this.storageService.storeCourseNote(request.getNotesFileName());
        String thumbnailFilename = this.storageService.storeCourseNote(request.getThumbnail());

        course.setThumbnail(thumbnailFilename);
        course.setAddedDateTime(addedDateTime);
        course.setNotesFileName(courseNote);
        course.setCategory(category);
        course.setMentor(mentor);
        course.setStatus(ActiveStatus.ACTIVE.value());

        Course savedCourse = this.courseService.add(course);

        if (savedCourse == null) {
            apiResponse.setResponseMessage("Failed to add the course");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            apiResponse.setCourse(savedCourse);
            apiResponse.setResponseMessage("Course Created Successful, Add Course Section now....");
            apiResponse.setSuccess(true);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.OK);
        }

    }

    public ResponseEntity<CourseResponseDto> addCourseSection(NewCourseSectionRequestDto request) {

        LOG.info("Received request to add course");

        CourseResponseDto apiResponse = new CourseResponseDto();

        if (request == null) {
            apiResponse.setResponseMessage("Missing request body");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        if (request.getCourseId() == 0 || request.getName() == null || request.getDescription() == null
                || request.getSrNo() == null) {
            apiResponse.setResponseMessage("Missing input");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        Course course = this.courseService.getById(request.getCourseId());

        if (course == null) {
            apiResponse.setResponseMessage("Course not found!!!");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        CourseSection section = new CourseSection();

        section.setCourse(course);
        section.setDescription(request.getDescription());
        section.setName(request.getName());
        section.setSrNo(request.getSrNo());

        CourseSection savedSection = this.courseSectionService.add(section);

        if (savedSection == null) {
            apiResponse.setCourse(course);
            apiResponse.setResponseMessage("Failed to add the course section");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {

            Course updatedCourse = this.courseService.getById(request.getCourseId());

            apiResponse.setCourse(updatedCourse);
            apiResponse.setResponseMessage("Course section added successful!!!");
            apiResponse.setSuccess(true);

            return new ResponseEntity<CourseResponseDto>(apiResponse, HttpStatus.OK);
        }

    }

    public ResponseEntity<CourseResponseDto> addCourseSectionTopic(NewCourseSectionTopicRequest request) {

        LOG.info("Received request to add course section topic");

        CourseResponseDto response = new CourseResponseDto();

        if (request == null) {
            response.setResponseMessage("Request body is missing");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        if (request.getSectionId() == 0 || request.getName() == null || request.getDescription() == null
                || request.getSrNo() == null || request.getVideo() == null) {
            response.setResponseMessage("Missing input fields");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        CourseSection section = this.courseSectionService.getById(request.getSectionId());

        if (section == null) {
            response.setResponseMessage("Course section not found");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        CourseSectionTopic topic = new CourseSectionTopic();
        topic.setName(request.getName());
        topic.setSrNo(request.getSrNo());
        topic.setDescription(request.getDescription());
        topic.setCourseSection(section);

        String topicVideoFileName = this.storageService.storeCourseVideo(request.getVideo());

        topic.setVideoFileName(topicVideoFileName);

        CourseSectionTopic savedTopic = this.courseSectionTopicService.add(topic);

        Course updatedCourse = this.courseService.getById(section.getCourse().getId());

        if (savedTopic == null) {
            response.setCourse(updatedCourse);
            response.setResponseMessage("Failed to add the course section topic");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response.setCourse(updatedCourse);
            response.setResponseMessage("Course section topic added successful!!!");
            response.setSuccess(true);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);
        }

    }

    public ResponseEntity<CourseResponseDto> fetchCourseById(Integer courseId, String toShowVideo) {

        LOG.info("Course retrieval request received using course ID");

        CourseResponseDto response = new CourseResponseDto();

        if (courseId == null || courseId == 0) {
            response.setResponseMessage("Course ID is missing");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        Course course = this.courseService.getById(courseId);

        if (course == null) {
            response.setResponseMessage("Course not found");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        List<CourseSection> sections = course.getSections();

        if (toShowVideo.equals(CourseTopicVideoShow.NO.value())) {
            if (!CollectionUtils.isEmpty(sections)) {

                for (CourseSection section : sections) {

                    List<CourseSectionTopic> topics = section.getCourseSectionTopics();

                    if (!CollectionUtils.isEmpty(topics)) {

                        for (CourseSectionTopic topic : topics) {
                            topic.setVideoFileName("");
                        }

                    }

                }

            }
        }

        response.setCourse(course);
        response.setResponseMessage("Course successfully fetched");
        response.setSuccess(true);

        return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);

    }

    public ResponseEntity<CourseResponseDto> fetchCoursesByStatus(String status, String videoShow) {

        LOG.info("Request received to fetch courses based on status");

        CourseResponseDto response = new CourseResponseDto();

        if (status == null || videoShow == null) {
            response.setResponseMessage("Required input is missing");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        List<Course> courses = this.courseService.getByStatus(status);

        if (CollectionUtils.isEmpty(courses)) {
            response.setResponseMessage("No courses found");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);
        }

        if (videoShow.equals(CourseTopicVideoShow.NO.value())) {

            for (Course course : courses) {
                List<CourseSection> sections = course.getSections();
                if (!CollectionUtils.isEmpty(sections)) {

                    for (CourseSection section : sections) {

                        List<CourseSectionTopic> topics = section.getCourseSectionTopics();

                        if (!CollectionUtils.isEmpty(topics)) {

                            for (CourseSectionTopic topic : topics) {
                                topic.setVideoFileName("");
                            }

                        }

                    }

                }
            }

        }

        response.setCourses(courses);
        response.setResponseMessage("Courses successfully fetched");
        response.setSuccess(true);

        return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);

    }

    public ResponseEntity<CourseResponseDto> fetchCoursesByMentor(Integer mentorId, String status, String videoShow) {

        LOG.info("Request received to fetch courses by mentor and status");

        CourseResponseDto response = new CourseResponseDto();

        if (mentorId == null || mentorId == 0 || status == null || videoShow == null) {
            response.setResponseMessage("Missing input");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        User mentor = this.userService.getUserById(mentorId);

        if (mentor == null || !mentor.getRole().equals(UserRole.ROLE_MENTOR.value())) {
            response.setResponseMessage("Mentor not found");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        List<Course> courses = this.courseService.getByMentorAndStatus(mentor, status);

        if (CollectionUtils.isEmpty(courses)) {
            response.setResponseMessage("Courses not found!!!");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);
        }

        if (videoShow.equals(CourseTopicVideoShow.NO.value())) {

            for (Course course : courses) {
                List<CourseSection> sections = course.getSections();
                if (!CollectionUtils.isEmpty(sections)) {

                    for (CourseSection section : sections) {

                        List<CourseSectionTopic> topics = section.getCourseSectionTopics();

                        if (!CollectionUtils.isEmpty(topics)) {

                            for (CourseSectionTopic topic : topics) {
                                topic.setVideoFileName("");
                            }

                        }

                    }

                }
            }

        }

        response.setCourses(courses);
        response.setResponseMessage("Courses successfully fetched");
        response.setSuccess(true);

        return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);

    }

    public ResponseEntity<CourseResponseDto> fetchCoursesByCategory(Integer categoryId, String videoShow) {

        LOG.info("Request received to fetch courses by category");

        CourseResponseDto response = new CourseResponseDto();

        if (categoryId == null || categoryId == 0 || videoShow == null) {
            response.setResponseMessage("Missing input");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        Category category = this.categoryService.getCategoryById(categoryId);

        if (category == null) {
            response.setResponseMessage("Category not found");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        List<Course> courses = this.courseService.getByCategoryAndStatus(category, ActiveStatus.ACTIVE.value());

        if (CollectionUtils.isEmpty(courses)) {
            response.setResponseMessage("Courses not found!!!");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);
        }

        if (videoShow.equals(CourseTopicVideoShow.NO.value())) {

            for (Course course : courses) {
                List<CourseSection> sections = course.getSections();
                if (!CollectionUtils.isEmpty(sections)) {

                    for (CourseSection section : sections) {

                        List<CourseSectionTopic> topics = section.getCourseSectionTopics();

                        if (!CollectionUtils.isEmpty(topics)) {

                            for (CourseSectionTopic topic : topics) {
                                topic.setVideoFileName("");
                            }

                        }

                    }

                }
            }

        }

        response.setCourses(courses);
        response.setResponseMessage("Courses successfully fetched");
        response.setSuccess(true);

        return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);

    }

    public ResponseEntity<CourseResponseDto> fetchCoursesByName(String courseName) {

        LOG.info("Request received to fetch courses by course name");

        CourseResponseDto response = new CourseResponseDto();

        if (courseName == null) {
            response.setResponseMessage("Missing input");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        List<Course> courses = this.courseService.getByNameAndStatus(courseName, ActiveStatus.ACTIVE.value());

        if (CollectionUtils.isEmpty(courses)) {
            response.setResponseMessage("Courses not found!!!");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);
        }

        for (Course course : courses) {
            List<CourseSection> sections = course.getSections();
            if (!CollectionUtils.isEmpty(sections)) {

                for (CourseSection section : sections) {

                    List<CourseSectionTopic> topics = section.getCourseSectionTopics();

                    if (!CollectionUtils.isEmpty(topics)) {

                        for (CourseSectionTopic topic : topics) {
                            topic.setVideoFileName("");
                        }

                    }

                }

            }
        }

        response.setCourses(courses);
        response.setResponseMessage("Courses successfully fetched");
        response.setSuccess(true);

        return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);

    }

    public void fetchCourseImage(String courseImageName, HttpServletResponse resp) {
        Resource resource = storageService.loadCourseNote(courseImageName);
        if (resource != null) {
            try (InputStream in = resource.getInputStream()) {
                ServletOutputStream out = resp.getOutputStream();
                FileCopyUtils.copy(in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fetchCourseTopicVideo(String courseSectionTopicVideoFileName, HttpServletResponse resp) {
        Resource resource = storageService.loadCourseVideo(courseSectionTopicVideoFileName);
        if (resource != null && resource.exists()) {
            try (InputStream in = resource.getInputStream(); ServletOutputStream out = resp.getOutputStream()) {
                resp.setContentType("video/mp4");
                FileCopyUtils.copy(in, out);
                out.flush();
            } catch (IOException e) {

                LOG.info("The video player was closed or there was a network issue");

                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public ResponseEntity<CourseResponseDto> fetchCourseByIdAndUserId(Integer courseId, Integer userId) {

        LOG.info("Request received to fetch the course by course id and user id");

        CourseResponseDto response = new CourseResponseDto();

        if (courseId == null || courseId == 0) {
            response.setResponseMessage("Course ID is missing");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        Course course = this.courseService.getById(courseId);

        if (course == null) {
            response.setResponseMessage("Course not found");
            response.setSuccess(false);

            return new ResponseEntity<CourseResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        List<CourseSection> sections = course.getSections();

        if (!CollectionUtils.isEmpty(sections)) {

            for (CourseSection section : sections) {

                List<CourseSectionTopic> topics = section.getCourseSectionTopics();

                if (!CollectionUtils.isEmpty(topics)) {

                    for (CourseSectionTopic topic : topics) {
                        topic.setVideoFileName("");
                    }

                }

            }

        }

        User student = null;

        if (userId > 0 && course.getType().equals(CourseType.PAID.value())) {
            student = this.userService.getUserById(userId);

            if (student != null) {

                List<Booking> bookings = this.bookingService.getByCourseAndCustomer(course, student);

                if (!CollectionUtils.isEmpty(bookings)) {
                    response.setIsCoursePurchased(CoursePurchased.YES.value());
                } else {
                    response.setIsCoursePurchased(CoursePurchased.NO.value());
                }

            }

        }

        response.setCourse(course);
        response.setResponseMessage("Course successfully fetched");
        response.setSuccess(true);

        return new ResponseEntity<CourseResponseDto>(response, HttpStatus.OK);

    }

    public ResponseEntity<CommonApiResponse> deleteCourse(Integer courseId) {

        LOG.info("Request received to delete the course by using course id");

        CommonApiResponse response = new CommonApiResponse();

        if (courseId == null || courseId == 0) {
            response.setResponseMessage("Course ID is missing");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        Course course = this.courseService.getById(courseId);

        if (course == null) {
            response.setResponseMessage("Course not found");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        course.setStatus(ActiveStatus.DEACTIVATED.value());
        this.courseService.update(course);

        response.setResponseMessage("Course successfully deleted");
        response.setSuccess(true);

        return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

    }

    public ResponseEntity<Resource> downloadNotes(String notesFileName, HttpServletResponse response) {

        Resource resource = storageService.loadCourseNote(notesFileName);
        if (resource == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Course_Notes\"")
                .body(resource);
    }

    public ResponseEntity<MentorResponseDto> fetchMentorDashboardData(Integer mentorId) {

        LOG.info("Request received to fetch mentor dashboard data");

        MentorResponseDto response = new MentorResponseDto();

        if (mentorId == null || mentorId == 0) {
            response.setResponseMessage("Mentor ID is missing");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User mentor = this.userService.getUserById(mentorId);

        if (mentor == null || !mentor.getRole().equals(UserRole.ROLE_MENTOR.value())) {
            response.setResponseMessage("Mentor not found");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Long totalActiveCourse = courseService.getCountByMentorAndStatus(mentor, ActiveStatus.ACTIVE.value());
        Long totalDeactivatedCourse = courseService.getCountByMentorAndStatus(mentor, ActiveStatus.DEACTIVATED.value());

        List<Booking> bookings = this.bookingService.getByMentor(mentor);

        response.setBookings(CollectionUtils.isEmpty(bookings) ? new ArrayList<>() : bookings);
        response.setTotalCoursePurchases(CollectionUtils.isEmpty(bookings) ? 0 : bookings.size());

        response.setTotalActiveCourse(totalActiveCourse != null ? totalActiveCourse : 0);
        response.setTotalDeletedCourse(totalDeactivatedCourse != null ? totalDeactivatedCourse : 0);
        response.setTotalPurchaseAmount(mentor.getAmount());
        response.setResponseMessage("Mentor dashboard data fetched successfully");
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
