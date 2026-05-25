package com.kintsugi.MiniKintsugi.exception;

public class UserAlreadyExistsException
        extends RuntimeException {

    public UserAlreadyExistsException(
            String message
    ) {
        super(message);
    }

}