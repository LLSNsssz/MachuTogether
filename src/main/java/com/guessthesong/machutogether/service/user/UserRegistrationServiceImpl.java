package com.guessthesong.machutogether.service.user;

import com.guessthesong.machutogether.domain.user.User;
import com.guessthesong.machutogether.domain.user.UserMapper;
import com.guessthesong.machutogether.domain.user.UserRegistrationDto;
import com.guessthesong.machutogether.exception.EmailAlreadyExistsException;
import com.guessthesong.machutogether.exception.NicknameAlreadyExistsException;
import com.guessthesong.machutogether.exception.PhoneNumberAlreadyExistsException;
import com.guessthesong.machutogether.exception.UsernameAlreadyExistsException;
import com.guessthesong.machutogether.repository.user.UserRepository;

import jakarta.transaction.Transactional;

import java.time.Instant;

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
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameAlreadyExistsException("이미 사용중인 아이디 입니다: " + dto.getUsername());
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("이미 사용중인 이메일 입니다: " + dto.getEmail());
        }
        if (userRepository.existsByNickname(dto.getNickname())) {
            throw new NicknameAlreadyExistsException("이미 사용중인 닉네임 입니다: " + dto.getNickname());
        }
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException("이미 사용중인 전화번호 입니다: " + dto.getPhoneNumber());
        }
    }
}
