package com.guessthesong.machutogether.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
