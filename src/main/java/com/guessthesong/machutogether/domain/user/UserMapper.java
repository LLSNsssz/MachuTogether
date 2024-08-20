package com.guessthesong.machutogether.domain.user;

public interface UserMapper {

    User toRegisterEntity(UserRegistrationDto dto);
    UserRegistrationDto toRegisterDto(User user);
}
