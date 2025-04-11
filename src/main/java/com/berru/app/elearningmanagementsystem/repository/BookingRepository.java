package com.berru.app.elearningmanagementsystem.repository;

import com.berru.app.elearningmanagementsystem.entity.Booking;
import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByReferenceCode(String referenceCode);

    List<Booking> findByCustomerOrderByIdDesc(User customer);

    @Query("SELECT b FROM Booking b WHERE b.course.mentor = :mentor")
    List<Booking> findAllBookingsByMentorOrderByIdDesc(@Param("mentor") User mentor);

    @Query("SELECT sum(b.amount) FROM Booking b WHERE b.course.mentor = :mentor")
    BigDecimal findSumOfAmountFromMentorBooking(@Param("mentor") User mentor);


    @Query("SELECT count(b) FROM Booking b WHERE b.course.mentor = :mentor")
    Long findBookingCountFromMentorBooking(@Param("mentor") User mentor);

    List<Booking> findByCourseOrderByIdDesc(Course course);

    List<Booking> findByCourseAndCustomer(Course course, User customer);
}
