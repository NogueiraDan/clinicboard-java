package com.clinicboard.clinicboard_java.modules.business.api.contract;

import java.util.List;

import com.clinicboard.clinicboard_java.modules.business.application.dto.PatientRequestDto;
import com.clinicboard.clinicboard_java.modules.business.application.dto.PatientResponseDto;
import com.clinicboard.clinicboard_java.modules.business.domain.repository.PatientRepository;

public interface PatientServiceInterface {
    
    PatientResponseDto save(PatientRequestDto patientRequestDto);
    
    List<PatientResponseDto> findAll();

    PatientResponseDto findById(String id);

    List<PatientResponseDto> findByProfessionalId(String id);

    List<PatientResponseDto> findByFilter(String param, String value);

    PatientResponseDto update(String id, PatientRequestDto patientRequestDto);

    void delete(String id);

    PatientRepository getPatientRepository();
}
