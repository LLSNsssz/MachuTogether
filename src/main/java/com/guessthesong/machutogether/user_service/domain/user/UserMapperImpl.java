package com.guessthesong.machutogether.user_service.domain.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toRegisterEntity(UserRegistrationDto dto) {
        return User.builder()
            .username(dto.getUsername())
            .nickname(dto.getNickname())
            .email(dto.getEmail())
            .password(dto.getPassword())
            .phoneNumber(dto.getPhoneNumber())
            .build();
    }

    @Override
    public UserRegistrationDto toRegisterDto(User user) {
        return new UserRegistrationDto(
            user.getUsername(),
            user.getNickname(),
            user.getEmail(),
            user.getPassword(),
            user.getPhoneNumber()
            );
    }
}
