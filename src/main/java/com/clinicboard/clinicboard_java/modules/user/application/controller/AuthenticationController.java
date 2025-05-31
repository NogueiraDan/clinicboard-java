package com.clinicboard.clinicboard_java.modules.user.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicboard.clinicboard_java.config.TokenService;
import com.clinicboard.clinicboard_java.modules.user.api.contract.UserServiceInterface;
import com.clinicboard.clinicboard_java.modules.user.api.dto.LoginRequestDto;
import com.clinicboard.clinicboard_java.modules.user.api.dto.LoginResponseDto;
import com.clinicboard.clinicboard_java.modules.user.api.dto.UserRequestDto;
import com.clinicboard.clinicboard_java.modules.user.api.dto.UserResponseDto;
import com.clinicboard.clinicboard_java.modules.user.domain.entity.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserServiceInterface userServiceInterface;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService,
            UserServiceInterface userServiceInterface) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userServiceInterface = userServiceInterface;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var userData = this.userServiceInterface.findByEmail(data.getEmail());
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok().body(new LoginResponseDto(userData.getId(), userData.getName(), userData.getEmail(),
                userData.getContact(), userData.getRole(), token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserRequestDto user) {
        if (userServiceInterface.getUserRepository().findByEmail(user.getEmail()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        UserRequestDto newUser = new UserRequestDto(user.getName(), user.getEmail(), encryptedPassword,
                user.getContact(), user.getRole());

        UserResponseDto savedUser = userServiceInterface.save(newUser);

        return ResponseEntity.ok().body(savedUser);

    }

}
