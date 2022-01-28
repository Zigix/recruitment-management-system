package com.example.recruitmentanagementsystem.domain.exception;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
