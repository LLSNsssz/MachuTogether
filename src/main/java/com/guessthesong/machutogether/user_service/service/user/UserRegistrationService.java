package com.guessthesong.machutogether.user_service.service.user;

import com.guessthesong.machutogether.user_service.domain.user.UserRegistrationDto;

public interface UserRegistrationService {

    UserRegistrationDto registerUser(UserRegistrationDto registrationDto);
}
