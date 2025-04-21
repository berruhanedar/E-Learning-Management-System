package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Booking;
import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.entity.User;
import com.berru.app.elearningmanagementsystem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking getById(int bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isPresent()) {
            return optionalBooking.get();
        } else {
            return null;
        }
    }

    public Booking findByBookingId(String bookingId) {
        return this.bookingRepository.findByBookingId(bookingId);
    }

    public List<Booking> getBookingByCustomer(User customer) {
        return bookingRepository.findByCustomerOrderByIdDesc(customer);
    }

    public List<Booking> getByMentor(User mentor) {
        return bookingRepository.findAllBookingsByMentorOrderByIdDesc(mentor);
    }

    public List<Booking> getByCourse(Course course) {
        return bookingRepository.findByCourseOrderByIdDesc(course);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getByCourseAndCustomer(Course course, User customer) {
        return bookingRepository.findByCourseAndCustomer(course, customer);
    }
}