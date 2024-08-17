package com.guessthesong.machutogether.service.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.guessthesong.machutogether.domain.user.User;
import com.guessthesong.machutogether.domain.user.UserMapper;
import com.guessthesong.machutogether.domain.user.UserRegistrationDto;
import com.guessthesong.machutogether.exception.DuplicateUserInfoException;
import com.guessthesong.machutogether.repository.user.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserRegistrationServiceImpl userRegistrationService;

    private UserRegistrationDto userRegistrationDto;

    @BeforeEach
    void setUp() {
        userRegistrationDto = new UserRegistrationDto(
            "testUser",
            "Test User",
            "test@test.com",
            "password123",
            "010-1111-1111"
        );
    }

    @Test
    void should_RegisterUser_When_InputIsValid() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.existsByNickname(anyString())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(User.builder().build());
        when(userMapper.toRegisterDto(any(User.class))).thenReturn(userRegistrationDto);

        UserRegistrationDto result = userRegistrationService.registerUser(
            userRegistrationDto);

        assertNotNull(result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void should_RegisterUser_when_UsernameAlreadyExists() {
        when(userRepository.existsByUsername("testUser")).thenReturn(true);

        assertThrows(DuplicateUserInfoException.class, () ->
            userRegistrationService.registerUser(userRegistrationDto)
        );
    }

    @Test
    void registerUser_EmailAlreadyExists() {
        when(userRepository.existsByEmail("test@test.com")).thenReturn(true);

        assertThrows(DuplicateUserInfoException.class, () ->
            userRegistrationService.registerUser(userRegistrationDto)
        );
    }

    @Test
    void registerUser_NicknameAlreadyExists() {
        when(userRepository.existsByNickname("Test User")).thenReturn(true);

        assertThrows(DuplicateUserInfoException.class, () ->
            userRegistrationService.registerUser(userRegistrationDto)
        );
    }

    @Test
    void registerUser_PhoneNumberAlreadyExists() {
        when(userRepository.existsByPhoneNumber("010-1111-1111")).thenReturn(true);

        assertThrows(DuplicateUserInfoException.class, () ->
            userRegistrationService.registerUser(userRegistrationDto)
        );
    }
}