package com.berru.app.elearningmanagementsystem.controller;

import com.berru.app.elearningmanagementsystem.dto.BookingResponseDto;
import com.berru.app.elearningmanagementsystem.dto.CommonApiResponse;
import com.berru.app.elearningmanagementsystem.dto.NewBookingRequestDto;
import com.berru.app.elearningmanagementsystem.facade.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/course/booking")
@CrossOrigin(origins = "http://localhost:8080")
public class BookingController {

    @Autowired
    private BookingFacade bookingFacade;

    @PostMapping("add")
    public ResponseEntity<CommonApiResponse> addEvent(@RequestBody NewBookingRequestDto request) {
        return this.bookingFacade.addBooking(request);
    }

    @GetMapping("fetch/all")
    public ResponseEntity<BookingResponseDto> fetchAllBookings() {
        return this.bookingFacade.fetchAllBookings();
    }

    @GetMapping("course-wise")
    public ResponseEntity<BookingResponseDto> fetchAllBookingsByCourse(@RequestParam("courseId") Integer courseId) {
        return this.bookingFacade.fetchAllBookingsByCourse(courseId);
    }

    @GetMapping("fetch/course-wise")
    public ResponseEntity<BookingResponseDto> fetchAllBookingsByCustomer(
            @RequestParam("customerId") Integer customerId) {
        return this.bookingFacade.fetchAllBookingsByCustomer(customerId);
    }

    @GetMapping("fetch/mentor-wise")
    public ResponseEntity<BookingResponseDto> fetchAllBookingsByMentorId(
            @RequestParam("mentorId") Integer mentorId) {
        return this.bookingFacade.fetchAllBookingsByMentorId(mentorId);
    }
}