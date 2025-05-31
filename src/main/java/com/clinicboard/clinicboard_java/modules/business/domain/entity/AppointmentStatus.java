package com.clinicboard.clinicboard_java.modules.business.domain.entity;

public enum AppointmentStatus {
    PENDING("Pendente"),
    SCHEDULED("Agendado"),
    CANCELED("Cancelado"),
    COMPLETED("Concluído"),
    NO_SHOW("Faltou"),
    RESCHEDULED("Remarcado");

    private final String status;

    AppointmentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
