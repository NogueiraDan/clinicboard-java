package com.clinicboard.clinicboard_java.modules.business.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.clinicboard.clinicboard_java.modules.business.application.dto.PatientRequestDto;
import com.clinicboard.clinicboard_java.modules.business.application.dto.PatientResponseDto;
import com.clinicboard.clinicboard_java.modules.business.domain.entity.Patient;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);
    
    @Mapping(target = "id", ignore = true)
    Patient toEntity(PatientRequestDto patientRequestDto);

    PatientResponseDto toDto(Patient patient);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professionalId", ignore = true)
    void updateEntity(PatientRequestDto patientRequestDto, @MappingTarget Patient patient);
}
