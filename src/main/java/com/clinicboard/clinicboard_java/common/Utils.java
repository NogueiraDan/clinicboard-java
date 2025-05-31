package com.clinicboard.clinicboard_java.common;

import org.springframework.security.core.userdetails.UserDetails;

import com.clinicboard.clinicboard_java.modules.user.api.dto.UserResponseDto;
import com.clinicboard.clinicboard_java.modules.user.domain.entity.User;

public class Utils {

    public static UserResponseDto convertToUserResponseDto(UserDetails userDetails) {
        if (userDetails instanceof User) {
            User user = (User) userDetails;
            return new UserResponseDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getContact(),
                    user.getRole());
        }
        return null;
    }

}