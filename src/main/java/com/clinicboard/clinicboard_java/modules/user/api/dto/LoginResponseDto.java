package com.clinicboard.clinicboard_java.modules.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private String id;
    private String name;
    private String email;
    private String contact;
    private UserRole role;
    private String access_token;
}
