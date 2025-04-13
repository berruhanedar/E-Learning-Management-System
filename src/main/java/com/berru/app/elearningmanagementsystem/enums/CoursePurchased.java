package com.berru.app.elearningmanagementsystem.enums;

public enum CoursePurchased {
    YES("Yes"), NO("No");

    private String show;

    private CoursePurchased(String show) {
        this.show = show;
    }

    public String value() {
        return this.show;
    }
}