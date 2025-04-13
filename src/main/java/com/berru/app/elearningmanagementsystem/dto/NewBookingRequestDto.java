package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.entity.User;
import com.berru.app.elearningmanagementsystem.enums.BookingStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class NewBookingRequestDto {

    private int courseId;

    private int customerId;

    private String cardNo;

    private String nameOnCard;

    private String cvv;

    private String expiryDate;

    private BigDecimal amount;

}
