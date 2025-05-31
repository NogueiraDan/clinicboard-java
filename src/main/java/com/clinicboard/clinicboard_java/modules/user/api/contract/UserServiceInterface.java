package com.clinicboard.clinicboard_java.modules.user.api.contract;

import java.util.List;

import com.clinicboard.clinicboard_java.modules.user.api.dto.UserRequestDto;
import com.clinicboard.clinicboard_java.modules.user.api.dto.UserResponseDto;
import com.clinicboard.clinicboard_java.modules.user.domain.repository.UserRepository;

public interface UserServiceInterface {

    UserResponseDto save(UserRequestDto user);

    UserResponseDto udpate(String id, UserRequestDto userRequestDto);

    void delete(String id);

    UserResponseDto findById(String id);

    List<UserResponseDto> findAll();

    UserRepository getUserRepository();

    UserResponseDto findByEmail(String email);
}
