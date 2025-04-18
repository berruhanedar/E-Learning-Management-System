package com.berru.app.elearningmanagementsystem.enums.status;

public enum ActiveStatus {
    ACTIVE("Active"),
    DEACTIVATED("Deactivated");

    private final String status;

    private ActiveStatus(String status) {
        this.status = status;
    }

    public String value() {
        return this.status;
    }
}