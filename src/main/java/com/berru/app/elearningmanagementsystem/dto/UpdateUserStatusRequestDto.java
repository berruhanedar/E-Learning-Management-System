package com.berru.app.elearningmanagementsystem.dto;

import lombok.Data;

@Data
public class UpdateUserStatusRequestDto {
    private int userId;

    private String status;
}
