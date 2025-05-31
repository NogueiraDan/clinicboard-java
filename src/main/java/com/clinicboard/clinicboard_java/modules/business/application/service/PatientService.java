package com.clinicboard.clinicboard_java.modules.business.application.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.clinicboard.clinicboard_java.common.error.BusinessException;
import com.clinicboard.clinicboard_java.common.error.CustomGenericException;
import com.clinicboard.clinicboard_java.modules.business.api.contract.PatientServiceInterface;
import com.clinicboard.clinicboard_java.modules.business.application.dto.PatientRequestDto;
import com.clinicboard.clinicboard_java.modules.business.application.dto.PatientResponseDto;
import com.clinicboard.clinicboard_java.modules.business.application.mapper.PatientMapper;
import com.clinicboard.clinicboard_java.modules.business.domain.entity.Patient;
import com.clinicboard.clinicboard_java.modules.business.domain.repository.PatientRepository;
import com.clinicboard.clinicboard_java.modules.user.api.contract.UserServiceInterface;
import com.clinicboard.clinicboard_java.modules.user.api.dto.UserResponseDto;
import com.clinicboard.clinicboard_java.modules.user.api.dto.UserRole;

@Service
public class PatientService implements PatientServiceInterface {

    private final UserServiceInterface userServiceInterface;
    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;

    public PatientService(UserServiceInterface userServiceInterface, PatientMapper patientMapper,
            PatientRepository patientRepository) {
        this.userServiceInterface = userServiceInterface;
        this.patientMapper = patientMapper;
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientResponseDto save(PatientRequestDto patient) {
        UserResponseDto userDto = userServiceInterface.findById(patient.getProfessionalId());
        if (userDto == null || userDto.getRole() != UserRole.PROFESSIONAL) {
            throw new BusinessException("Usuário não encontrado ou o perfil não tem permissão");
        }
        return patientMapper.toDto(patientRepository.save(patientMapper.toEntity(patient)));
    }

    @Override
    public List<PatientResponseDto> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .toList();
    }

    @Override
    public PatientResponseDto findById(String id) {
        return patientMapper.toDto(patientRepository.findById(id)
                .orElseThrow(() -> new CustomGenericException("Paciente não encontrado")));
    }

    @Override
    public List<PatientResponseDto> findByFilter(String param, String value) {
        List<Patient> patients;

        value = "%" + value + "%";
        switch (param.toLowerCase()) {
            case "nome":
                patients = patientRepository.findByName(value);
                break;
            case "email":
                patients = patientRepository.findByEmail(value);
                break;
            case "contato":
                patients = patientRepository.findByContact(value);
                break;
            default:
                throw new CustomGenericException("Parâmetro de busca inválido. Use 'nome', 'email' ou 'contato'.");
        }

        return patients.stream()
                .map(patientMapper::toDto)
                .toList();
    }

    @Override
    public PatientResponseDto update(String id, PatientRequestDto patient) {
        var existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new CustomGenericException("Paciente não encontrado"));
        patientMapper.updateEntity(patient, existingPatient);
        return patientMapper.toDto(patientRepository.save(existingPatient));
    }

    @Override
    public void delete(String id) {
        if (!patientRepository.existsById(id)) {
            throw new CustomGenericException("Paciente não encontrado");
        }
        patientRepository.deleteById(id);
    }

    @Override
    public PatientRepository getPatientRepository() {
        return this.patientRepository;
    }

    @Override
    public List<PatientResponseDto> findByProfessionalId(String id) {
        return patientRepository.findByProfessionalId(id).stream()
                .map(patientMapper::toDto)
                .toList();
    }

}
