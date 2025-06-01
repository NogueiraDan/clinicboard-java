package com.clinicboard.clinicboard_java.modules.business.domain.entity;

public enum AppointmentType {
    MARCACAO("Marcação"),
    REMARCACAO("Remarcação");

    private final String type;

    AppointmentType(String type) {
        this.type = type;
    }

    public String gettype() {
        return type;
    }
}
