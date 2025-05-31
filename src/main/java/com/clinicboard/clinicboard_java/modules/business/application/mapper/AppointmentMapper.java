package com.clinicboard.clinicboard_java.modules.business.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.clinicboard.clinicboard_java.modules.business.application.dto.AppointmentRequestDto;
import com.clinicboard.clinicboard_java.modules.business.application.dto.AppointmentResponseDto;
import com.clinicboard.clinicboard_java.modules.business.domain.entity.Appointment;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    Appointment toEntity(AppointmentRequestDto appointmentRequestDto);

    AppointmentResponseDto toDto(Appointment appointment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professionalId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateEntity(AppointmentRequestDto appointmentRequestDto, @MappingTarget Appointment appointment);

}
