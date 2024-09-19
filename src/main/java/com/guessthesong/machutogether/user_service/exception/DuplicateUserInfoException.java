package com.guessthesong.machutogether.user_service.exception;

public class DuplicateUserInfoException extends RuntimeException{

    public DuplicateUserInfoException(String message) {
        super(message);
    }
}
