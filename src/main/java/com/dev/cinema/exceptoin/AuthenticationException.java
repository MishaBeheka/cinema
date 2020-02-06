package com.dev.cinema.exceptoin;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Exception e) {
        super(message);
    }
}
