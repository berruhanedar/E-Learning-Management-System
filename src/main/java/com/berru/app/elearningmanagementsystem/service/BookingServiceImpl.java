package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Booking;
import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.entity.User;
import com.berru.app.elearningmanagementsystem.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;

    @Override
    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getById(int bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isPresent()) {
            return optionalBooking.get();
        } else {
            return null;
        }
    }

    @Override
    public Booking findByReferenceCode(String bookingId) {
        return this.bookingRepository.findByReferenceCode(bookingId);
    }

    @Override
    public List<Booking> getBookingByCustomer(User customer) {
        return bookingRepository.findByCustomerOrderByIdDesc(customer);
    }

    @Override
    public List<Booking> getByMentor(User mentor) {
        return bookingRepository.findAllBookingsByMentorOrderByIdDesc(mentor);
    }

    @Override
    public List<Booking> getByCourse(Course course) {
        return bookingRepository.findByCourseOrderByIdDesc(course);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getByCourseAndCustomer(Course course, User customer) {
        return bookingRepository.findByCourseAndCustomer(course, customer);
    }

}