package com.berru.app.elearningmanagementsystem.dto;

import lombok.Data;

import java.math.BigDecimal;

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
