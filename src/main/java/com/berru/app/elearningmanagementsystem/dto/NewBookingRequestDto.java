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

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Booked date cannot be null")
    private LocalDateTime bookedAt;

    @NotNull(message = "Course cannot be null")
    private Course course_id;

    @NotNull(message = "Customer cannot be null")
    private User customer_id;

    @NotBlank(message = "Card holder name cannot be blank")
    @Size(min = 2, max = 100, message = "Card holder name must be between 2 and 100 characters")
    private String cardHolderName;

    @NotBlank(message = "Card number cannot be blank")
    @Pattern(regexp = "^(\\d{16})$", message = "Card number must be exactly 16 digits")
    private String cardNumber;

    @NotNull(message = "Booking status cannot be null")
    private BookingStatus status;
}
