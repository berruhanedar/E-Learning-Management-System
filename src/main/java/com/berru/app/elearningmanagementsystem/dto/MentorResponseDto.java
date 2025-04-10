package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.Booking;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class MentorResponseDto extends CommonApiResponse {

    private Long totalActiveCourse;

    private Long totalDeletedCourse;

    private Integer totalCoursePurchases;

    private BigDecimal totalPurchaseAmount;

    private List<Booking> bookings = new ArrayList<>();

}