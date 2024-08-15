package com.guessthesong.machutogether.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegistrationDto {

    @Size(min = 6, max = 20, message = "사용자 이름은 6자에서 20자 사이여야 합니다.")
    @Pattern(message = "아이디는 영문자와 숫자만 사용 가능합니다.", regexp = "^[a-zA-Z0-9]+$")
    @NotBlank(message = "사용자 이름은 필수 입력 항목입니다.")
    private String username;

    @Size(min = 2, max = 20, message = "닉네임은 2자에서 20자 사이여야 합니다.")
    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    private String nickname;

    @Email(message = "유효하지 않은 이메일 형식입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
        message = "올바른 이메일 주소를 입력해주세요.")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    private String email;

    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$",
        message = "비밀번호는 문자, 숫자, 특수문자 중 2종류 이상을 조합해야 합니다.")
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다. (예: 010-1234-5678)")
    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    private String phoneNumber;
}