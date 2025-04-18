package com.berru.app.elearningmanagementsystem.enums.type;

public enum CourseType {
    PAID("Paid"),
    FREE("Free");

    private String type;

    private CourseType(String type) {
        this.type = type;
    }

    public String value() {
        return this.type;
    }
}