package com.berru.app.elearningmanagementsystem.enums.role;

public enum UserRole {
    ROLE_STUDENT("Student"),
    ROLE_ADMIN("Admin"),
    ROLE_MENTOR("Mentor");

    private String role;

    private UserRole(String role) {
        this.role = role;
    }

    public String value() {
        return this.role;
    }
}
