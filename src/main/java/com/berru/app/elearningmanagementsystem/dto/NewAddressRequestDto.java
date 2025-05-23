package com.berru.app.elearningmanagementsystem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class NewAddressRequestDto {

    @Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid street")
    private String street;

    @NotNull(message = "City name cannot be null")
    @Pattern(regexp = "[A-Za-z\\s]{2,}", message = "Not a valid city name")
    private String city;

    @NotNull(message = "Postal code cannot be null")
    @Pattern(regexp = "[0-9]{6}", message = "Postal code not valid. Must be 6 digits")
    private String postalCode;
}