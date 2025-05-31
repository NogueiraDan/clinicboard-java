package com.clinicboard.clinicboard_java.modules.user.api.dto;

public enum UserRole {
    ADMIN("admin"),
    PROFESSIONAL("professional");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}