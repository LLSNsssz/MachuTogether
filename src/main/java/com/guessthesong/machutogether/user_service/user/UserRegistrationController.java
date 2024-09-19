package com.guessthesong.machutogether.user_service.user;

import com.guessthesong.machutogether.user_service.domain.user.UserRegistrationDto;
import com.guessthesong.machutogether.user_service.service.user.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRegistrationController {


    private final UserRegistrationService userService;

    public UserRegistrationController(UserRegistrationService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationDto> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        UserRegistrationDto createdUser = userService.registerUser(registrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
