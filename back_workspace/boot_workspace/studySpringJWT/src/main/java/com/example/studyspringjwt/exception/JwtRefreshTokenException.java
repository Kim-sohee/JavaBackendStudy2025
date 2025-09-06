package com.example.studyspringjwt.exception;

public class JwtRefreshTokenException extends RuntimeException {
    public JwtRefreshTokenException(String message) {
        super(message);
    }

    public JwtRefreshTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtRefreshTokenException(Throwable cause) {
        super(cause);
    }
}
