package com.berru.app.elearningmanagementsystem.enums;

public enum ActiveStatus {
    ACTIVE("Active"), DEACTIVATED("Deactivated");

    private String status;

    private ActiveStatus(String status) {
        this.status = status;
    }

    public String value() {
        return this.status;
    }
}