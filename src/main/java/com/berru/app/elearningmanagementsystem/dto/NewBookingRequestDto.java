package com.berru.app.elearningmanagementsystem.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewBookingRequestDto {

    @NotNull(message = "Course ID cannot be null")
    private int courseId;

    @NotNull(message = "Customer ID cannot be null")
    private int customerId;

    @NotNull(message = "Card number cannot be null")
    @Pattern(regexp = "[0-9]{16}", message = "Card number must be 16 digits")
    private String cardNo;

    @NotNull(message = "Name on card cannot be null")
    @Pattern(regexp = "[A-Za-z\\s'-]{2,}", message = "Invalid name on card")
    private String nameOnCard;

    @NotNull(message = "CVV cannot be null")
    @Pattern(regexp = "[0-9]{3}", message = "CVV must be 3 digits")
    private String cvv;

    @NotNull(message = "Expiry date cannot be null")
    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}", message = "Expiry date must be in MM/YY format")
    private String expiryDate;

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

}
