package com.guessthesong.machutogether.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserMapperImplTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapperImpl();
    }

    @Test
    void testToEntity() {
        UserRegistrationDto dto = new UserRegistrationDto(
            "testuser",
            "Test User",
            "test@example.com",
            "password123!",
            "010-1234-5678"
        );

        User user = userMapper.toRegisterEntity(dto);

        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(dto.getUsername());
        assertThat(user.getNickname()).isEqualTo(dto.getNickname());
        assertThat(user.getEmail()).isEqualTo(dto.getEmail());
        assertThat(user.getPassword()).isEqualTo(dto.getPassword());
        assertThat(user.getPhoneNumber()).isEqualTo(dto.getPhoneNumber());
        assertThat(user.getId()).isNull();
        assertThat(user.getCreatedAt()).isNull();
        assertThat(user.getIsAdmin()).isNull();
    }

    @Test
    void testToDto() {
        User user = User.builder()
            .username("testuser")
            .nickname("Test User")
            .email("test@example.com")
            .password("hashedPassword")
            .phoneNumber("010-1234-5678")
            .createdAt(Instant.now())
            .isAdmin(false)
            .build();

        UserRegistrationDto registerDto = userMapper.toRegisterDto(user);

        assertThat(registerDto).isNotNull();
        assertThat(registerDto.getUsername()).isEqualTo("testuser");
        assertThat(registerDto.getNickname()).isEqualTo("Test User");
        assertThat(registerDto.getEmail()).isEqualTo("test@example.com");
        assertThat(registerDto.getPassword()).isEqualTo("hashedPassword");
        assertThat(registerDto.getPhoneNumber()).isEqualTo("010-1234-5678");
    }
}