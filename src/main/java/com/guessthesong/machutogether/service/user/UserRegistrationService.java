package com.guessthesong.machutogether.service.user;

import com.guessthesong.machutogether.domain.user.UserRegistrationDto;

public interface UserRegistrationService {

    UserRegistrationDto registerUser(UserRegistrationDto registrationDto);
}
