package com.guessthesong.machutogether.controller.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guessthesong.machutogether.domain.user.UserRegistrationDto;
import com.guessthesong.machutogether.service.user.UserRegistrationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(UserRegistrationController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRegistrationService userRegistrationService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRegistrationDto validUserDto;

    @BeforeEach
    void setUp() {
        validUserDto = new UserRegistrationDto(
            "testuser",
            "TestNick",
            "test@email.com",
            "12341234qwer",
            "010-3333-3333");
    }

    @Test
    void whenValidInput_thenReturn201() throws Exception {
        when(userRegistrationService.registerUser(any(UserRegistrationDto.class))).thenReturn(
            validUserDto);

        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validUserDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.username").value("testuser"))
            .andExpect(jsonPath("$.email").value("test@email.com"));
    }

    @Test
    void whenInvalidInput_thenReturn400() throws Exception {
        UserRegistrationDto invalidUser = new UserRegistrationDto("", "", "", "", "");

        ResultActions result = mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidUser)))
            .andExpect(status().isBadRequest());

    }
}
