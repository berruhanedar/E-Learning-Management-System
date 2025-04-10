package com.berru.app.elearningmanagementsystem.dto;

import com.berru.app.elearningmanagementsystem.entity.Booking;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookingResponseDto extends CommonApiResponse {

    private List<Booking> bookings = new ArrayList();

}