package com.guessthesong.machutogether.service.user;

import com.guessthesong.machutogether.domain.user.User;
import com.guessthesong.machutogether.exception.InvalidPasswordException;
import com.guessthesong.machutogether.exception.UserNotFoundException;
import com.guessthesong.machutogether.repository.user.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserLoginServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("입력한 아이디가 존재하지 않습니다"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다");
        }

        return true;
    }
}
