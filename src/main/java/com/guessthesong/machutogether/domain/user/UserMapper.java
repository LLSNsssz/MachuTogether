package com.guessthesong.machutogether.domain.user;

import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    User toRegisterEntity(UserRegistrationDto dto);
    UserRegistrationDto toRegisterDto(User user);
}
