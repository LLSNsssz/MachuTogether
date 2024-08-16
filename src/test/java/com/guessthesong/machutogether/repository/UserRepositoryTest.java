package com.guessthesong.machutogether.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.guessthesong.machutogether.domain.user.User;
import com.guessthesong.machutogether.repository.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-dev.yml")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
            .username("test_user")
            .nickname("Test User")
            .email("test@example.com")
            .password("password123!")
            .phoneNumber("010-1000-1000")
            .createdAt(Instant.now())
            .isAdmin(false)
            .build();

        userRepository.save(user);
    }

    @Test
    void whenFindByUsername_thenReturnUser() {

        // when
        User found = userRepository.findById(user.getId()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void whenFindByEmail_thenReturnUser() {

        // when
        User found = userRepository.findByEmail(user.getEmail()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getNickname()).isEqualTo(user.getNickname());
    }

    @Test
    void whenFindByNickname_thenReturnUser() {

        // when
        User found = userRepository.findByNickname(user.getNickname()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }

    @Test
    void whenFindByPhoneNumber_thenReturnUser() {

        // when
        User found = userRepository.findByPhoneNumber(user.getPhoneNumber()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void existsByUsername_shouldReturnTrue_whenUsernameExists() {
        boolean exists = userRepository.existsByUsername(user.getUsername());
        assertThat(exists).isTrue();
    }

    @Test
    void existsByUsername_shouldReturnFalse_whenUsernameDoesNotExist() {
        boolean exists = userRepository.existsByUsername("non_existent_user");
        assertThat(exists).isFalse();
    }

    @Test
    void existsByEmail_shouldReturnTrue_whenEmailExists() {
        boolean exists = userRepository.existsByEmail(user.getEmail());
        assertThat(exists).isTrue();
    }

    @Test
    void existsByEmail_shouldReturnFalse_whenEmailDoesNotExist() {
        boolean exists = userRepository.existsByEmail("non_existent@example.com");
        assertThat(exists).isFalse();
    }

    @Test
    void existsByNickname_shouldReturnTrue_whenNicknameExists() {
        boolean exists = userRepository.existsByNickname(user.getNickname());
        assertThat(exists).isTrue();
    }

    @Test
    void existsByNickname_shouldReturnFalse_whenNicknameDoesNotExist() {
        boolean exists = userRepository.existsByNickname("Non Existent User");
        assertThat(exists).isFalse();
    }

    @Test
    void existsByPhoneNumber_shouldReturnTrue_whenPhoneNumberExists() {
        boolean exists = userRepository.existsByPhoneNumber(user.getPhoneNumber());
        assertThat(exists).isTrue();
    }

    @Test
    void existsByPhoneNumber_shouldReturnFalse_whenPhoneNumberDoesNotExist() {
        boolean exists = userRepository.existsByPhoneNumber("010-9999-9999");
        assertThat(exists).isFalse();
    }

}