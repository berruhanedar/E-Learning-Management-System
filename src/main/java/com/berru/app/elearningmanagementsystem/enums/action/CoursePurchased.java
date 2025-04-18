package com.berru.app.elearningmanagementsystem.enums.action;

public enum CoursePurchased {
    YES("Yes"),
    NO("No");

    private final String show;

    private CoursePurchased(String show) {
        this.show = show;
    }

    public String value() {
        return this.show;
    }
}