package com.guessthesong.machutogether.user_service.domain.user;

public interface UserMapper {

    User toRegisterEntity(UserRegistrationDto dto);
    UserRegistrationDto toRegisterDto(User user);
}
