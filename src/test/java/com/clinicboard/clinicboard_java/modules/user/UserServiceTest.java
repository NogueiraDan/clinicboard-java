package com.clinicboard.clinicboard_java.modules.user;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.clinicboard.clinicboard_java.common.error.CustomGenericException;
import com.clinicboard.clinicboard_java.modules.user.api.dto.UserRequestDto;
import com.clinicboard.clinicboard_java.modules.user.api.dto.UserResponseDto;
import com.clinicboard.clinicboard_java.modules.user.application.mapper.UserMapper;
import com.clinicboard.clinicboard_java.modules.user.application.service.UserService;
import com.clinicboard.clinicboard_java.modules.user.domain.repository.UserRepository;
import com.clinicboard.clinicboard_java.modules.user.domain.entity.User;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserRepository() {
        assertEquals(userRepository, userService.getUserRepository());
    }

    @Test
    void testFindByEmail() {
        String email = "test@example.com";
        User user = new User();
        UserResponseDto expectedDto = new UserResponseDto();

        when(userRepository.findByEmail(email)).thenReturn(user);

        UserResponseDto result = userService.findByEmail(email);

        assertEquals(expectedDto.getEmail(), result.getEmail());

        verify(userRepository).findByEmail(email);
    }

    @Test
    void testSave() {
        UserRequestDto userRequestDto = new UserRequestDto();
        User user = new User();
        User savedUser = new User();
        UserResponseDto userResponseDto = new UserResponseDto();

        when(userMapper.toEntity(userRequestDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toDto(savedUser)).thenReturn(userResponseDto);

        UserResponseDto result = userService.save(userRequestDto);

        assertEquals(userResponseDto, result);
        verify(userRepository).save(user);
    }

    @Test
    void testFindById_Success() {
        String id = "1";
        User user = new User();
        UserResponseDto userResponseDto = new UserResponseDto();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userResponseDto);

        UserResponseDto result = userService.findById(id);

        assertEquals(userResponseDto, result);
        verify(userRepository).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        String id = "1";
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomGenericException.class, () -> userService.findById(id));
        verify(userRepository).findById(id);
    }

    @Test
    void testUpdate_Success() {
        String id = "1";
        UserRequestDto userRequestDto = new UserRequestDto();
        User existingUser = new User();
        User updatedUser = new User();
        UserResponseDto userResponseDto = new UserResponseDto();

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        // updateEntityFromDto é void, então não precisa de when
        when(userRepository.save(existingUser)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(userResponseDto);

        UserResponseDto result = userService.udpate(id, userRequestDto);

        assertEquals(userResponseDto, result);
        verify(userRepository).findById(id);
        verify(userRepository).save(existingUser);
    }

    @Test
    void testUpdate_NotFound() {
        String id = "1";
        UserRequestDto userRequestDto = new UserRequestDto();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomGenericException.class, () -> userService.udpate(id, userRequestDto));
        verify(userRepository).findById(id);
    }

    @Test
    void testDelete_Success() {
        String id = "1";
        when(userRepository.existsById(id)).thenReturn(true);

        userService.delete(id);

        verify(userRepository).existsById(id);
        verify(userRepository).deleteById(id);
    }

    @Test
    void testDelete_NotFound() {
        String id = "1";
        when(userRepository.existsById(id)).thenReturn(false);

        assertThrows(CustomGenericException.class, () -> userService.delete(id));
        verify(userRepository).existsById(id);
    }
}