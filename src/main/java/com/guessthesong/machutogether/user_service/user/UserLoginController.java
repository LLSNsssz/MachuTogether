package com.guessthesong.machutogether.user_service.user;

import com.guessthesong.machutogether.user_service.domain.user.UserLoginDto;
import com.guessthesong.machutogether.user_service.exception.InvalidPasswordException;
import com.guessthesong.machutogether.user_service.exception.UserNotFoundException;
import com.guessthesong.machutogether.user_service.service.user.UserLoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserLoginController {

    private final UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        try {
            if (userLoginService.authenticate(userLoginDto.getUsername(), userLoginDto.getPassword())) {
                return ResponseEntity.ok().body("Login successful");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }
}
