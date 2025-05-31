package com.clinicboard.clinicboard_java.modules.business.api.contract;

import java.time.LocalTime;
import java.util.List;

import com.clinicboard.clinicboard_java.modules.business.application.dto.AppointmentRequestDto;
import com.clinicboard.clinicboard_java.modules.business.application.dto.AppointmentResponseDto;
import com.clinicboard.clinicboard_java.modules.business.domain.repository.AppointmentRepository;

public interface AppointmentServiceInterface {

    AppointmentResponseDto save(AppointmentRequestDto appointmentRequestDto);

    List<AppointmentResponseDto> findAll();

    AppointmentResponseDto findById(String id);

    List<AppointmentResponseDto> findByProfessionalId(String id);

    List<AppointmentResponseDto> findByPatientId(String id);

    List<AppointmentResponseDto> findByFilter(String id, String param, String value);

    List<AppointmentResponseDto> findByDate(String id, String date);

    AppointmentResponseDto update(String id, AppointmentRequestDto appointmentRequestDto);

    void delete(String id);

    AppointmentRepository getAppointmentRepository();

    List<LocalTime> getAvailableTimes(String professionalId, String date);
}
