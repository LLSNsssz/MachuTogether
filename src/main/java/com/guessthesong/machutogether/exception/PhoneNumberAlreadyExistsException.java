package com.guessthesong.machutogether.exception;

public class PhoneNumberAlreadyExistsException extends RuntimeException{

    public PhoneNumberAlreadyExistsException(String message) {
        super(message);
    }
}
