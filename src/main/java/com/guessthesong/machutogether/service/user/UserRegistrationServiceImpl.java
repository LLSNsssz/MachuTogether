package com.guessthesong.machutogether.service.user;

import com.guessthesong.machutogether.domain.user.User;
import com.guessthesong.machutogether.domain.user.UserMapper;
import com.guessthesong.machutogether.domain.user.UserRegistrationDto;
import com.guessthesong.machutogether.exception.DuplicateUserInfoException;
import com.guessthesong.machutogether.repository.user.UserRepository;

import jakarta.transaction.Transactional;

import java.time.Instant;

import java.util.function.Predicate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserRegistrationServiceImpl(UserRepository userRepository,
        PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserRegistrationDto registerUser(UserRegistrationDto registrationDto) {
        checkDuplicates(registrationDto);

        User user = User.builder()
            .username(registrationDto.getUsername())
            .nickname(registrationDto.getNickname())
            .email(registrationDto.getEmail())
            .phoneNumber(registrationDto.getPhoneNumber())
            .password(getEncodePassword(registrationDto))
            .createdAt(Instant.now())
            .isAdmin(false)
            .build();

        User savedUser = userRepository.save(user);
        return userMapper.toRegisterDto(savedUser);
    }

    private String getEncodePassword(UserRegistrationDto registrationDto) {
        return passwordEncoder.encode(registrationDto.getPassword());
    }

    private void checkDuplicates(UserRegistrationDto dto) {
        checkDuplicate("username", dto.getUsername(), userRepository::existsByUsername);
        checkDuplicate("email", dto.getEmail(), userRepository::existsByEmail);
        checkDuplicate("nickname", dto.getNickname(), userRepository::existsByNickname);
        checkDuplicate("phone number", dto.getPhoneNumber(), userRepository::existsByPhoneNumber);

    }

    private void checkDuplicate(String field, String value, Predicate<String> existsCheck) {
        if (existsCheck.test(value)) {
            throw new DuplicateUserInfoException(String.format("이미 사용중인 %s 입니다: %s", field, value));
        }
    }
}
