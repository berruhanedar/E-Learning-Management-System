package com.berru.app.elearningmanagementsystem.enums;

public enum BookingStatus {
    CONFIRMED("Confirmed"), CANCELLED("Cancelled");

    private String status;

    private BookingStatus(String status) {
        this.status = status;
    }

    public String value() {
        return this.status;
    }
}
