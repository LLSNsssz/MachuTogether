package com.guessthesong.machutogether.exception;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
