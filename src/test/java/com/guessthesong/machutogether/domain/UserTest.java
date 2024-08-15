package com.guessthesong.machutogether.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.guessthesong.machutogether.domain.user.User;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void createUser() {
        User user = User.builder()
            .username("testuser")
            .nickname("Test User")
            .email("test@example.com")
            .password("password123!")
            .phoneNumber("010-9999-9999")
            .createdAt(Instant.now())
            .isAdmin(false)
            .build();

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        assertEquals("Test User", user.getNickname());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("010-9999-9999", user.getPhoneNumber());
        assertNotNull(user.getCreatedAt());
        assertNull(user.getLastLogin());
        assertFalse(user.getIsAdmin());
    }
}