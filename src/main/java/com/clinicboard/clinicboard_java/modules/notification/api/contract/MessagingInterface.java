package com.clinicboard.clinicboard_java.modules.notification.api.contract;

import com.clinicboard.clinicboard_java.modules.business.application.dto.AppointmentRequestDto;

public interface MessagingInterface {
    void publishNotification(AppointmentRequestDto appointment);
}
