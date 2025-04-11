package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Booking;
import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.entity.User;

import java.util.List;

public interface BookingService {

    Booking addBooking(Booking booking);

    Booking updateBooking(Booking booking);

    Booking getById(int bookingId);

    Booking findByReferenceCode(String referenceCode);

    List<Booking> getBookingByCustomer(User customer);

    List<Booking> getByMentor(User mentor);

    List<Booking> getByCourse(Course course);

    List<Booking> getAllBookings();

    List<Booking> getByCourseAndCustomer(Course course, User customer);

}