package com.guessthesong.machutogether.service.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.guessthesong.machutogether.domain.user.User;
import com.guessthesong.machutogether.exception.InvalidPasswordException;
import com.guessthesong.machutogether.exception.UserNotFoundException;
import com.guessthesong.machutogether.repository.user.UserRepository;

import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.crypto.password.PasswordEncoder;

class UserLoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserLoginServiceImpl userLoginService;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        userLoginService = new UserLoginServiceImpl(userRepository, passwordEncoder);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void whenValidCredentials_thenAuthenticateReturnsTrue() {
        User user = User.builder()
            .username("testuser")
            .nickname("Test User")
            .email("test@example.com")
            .phoneNumber("1234567890")
            .password("encodedPassword")
            .createdAt(Instant.now())
            .isAdmin(false)
            .build();

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        assertTrue(userLoginService.authenticate("testuser", "password"));
    }

    @Test
    void whenInvalidUsername_thenThrowsUserNotFoundException() {
        when(userRepository.findByUsername("nonexistentuser")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
            () -> userLoginService.authenticate("nonexistentuser", "anypassword"));
    }

    @Test
    void whenInvalidPassword_thenThrowsPasswordInvalidException() {
        User user = User.builder()
            .username("testuser")
            .nickname("Test User")
            .email("test@example.com")
            .phoneNumber("1234567890")
            .password("encodedPassword")
            .createdAt(Instant.now())
            .isAdmin(false)
            .build();

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpassword", "encodedPassword")).thenReturn(false);

        assertThrows(InvalidPasswordException.class,
            () -> userLoginService.authenticate("testuser", "wrongpassword"));
    }
}
