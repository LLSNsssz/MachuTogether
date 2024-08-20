package com.guessthesong.machutogether;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guessthesong.machutogether.domain.user.UserLoginDto;
import com.guessthesong.machutogether.domain.user.UserRegistrationDto;
import com.guessthesong.machutogether.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    private UserRegistrationDto validUserDto;
    private UserLoginDto validLoginDto;

    @BeforeEach
    void setUp() {
        validUserDto = UserRegistrationDto.builder()
            .username("testuser123")
            .nickname("TestNick")
            .email("test@example.com")
            .password("Password123!")
            .phoneNumber("010-1234-5678")
            .build();

        validLoginDto = new UserLoginDto("testuser123", "Password123!");
    }

    @Test
    void whenRegisterUser_thenUserIsSavedToDatabase() throws Exception {
        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validUserDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.username").value("testuser123"))
            .andExpect(jsonPath("$.email").value("test@example.com"));

        assert userRepository.findByUsername("testuser123").isPresent();
    }

    @Test
    void whenLoginWithInvalidUsername_thenNotFound() throws Exception {
        UserLoginDto invalidLoginDto = new UserLoginDto("nonexistentuser", "Password123!");

        mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidLoginDto)))
            .andExpect(status().isNotFound())
            .andExpect(content().string("입력한 아이디가 존재하지 않습니다"));
    }

    @Test
    void whenLoginWithInvalidPassword_thenUnauthorized() throws Exception {
        // 먼저 사용자 등록
        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validUserDto)))
            .andExpect(status().isCreated());

        // validLoginDto의 비밀번호만 변경하여 잘못된 비밀번호로 로그인 시도
        UserLoginDto invalidLoginDto = new UserLoginDto(validLoginDto.getUsername(), "wrongpassword");

        mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidLoginDto)))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string("비밀번호가 일치하지 않습니다"));
    }
}


