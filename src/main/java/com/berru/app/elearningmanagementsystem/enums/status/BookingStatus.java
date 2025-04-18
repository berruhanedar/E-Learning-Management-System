package com.berru.app.elearningmanagementsystem.enums.status;

public enum BookingStatus {
    CONFIRMED("Confirmed"),
    CANCELLED("Cancelled");

    private final String status;

    private BookingStatus(String status) {
        this.status = status;
    }

    public String value() {
        return this.status;
    }
}
