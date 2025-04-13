package com.berru.app.elearningmanagementsystem.enums;

public enum CourseType {
    PAID("Paid"), FREE("Free");

    private String type;

    private CourseType(String type) {
        this.type = type;
    }

    public String value() {
        return this.type;
    }
}