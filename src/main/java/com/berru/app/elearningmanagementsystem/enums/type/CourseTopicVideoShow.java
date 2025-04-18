package com.berru.app.elearningmanagementsystem.enums.type;

public enum CourseTopicVideoShow {
    YES("Yes"),
    NO("No");

    private String show;

    private CourseTopicVideoShow(String show) {
        this.show = show;
    }

    public String value() {
        return this.show;
    }
}