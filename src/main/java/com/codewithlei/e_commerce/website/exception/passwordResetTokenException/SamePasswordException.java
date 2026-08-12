package com.codewithlei.e_commerce.website.exception.passwordResetTokenException;

public class SamePasswordException extends RuntimeException {
    public SamePasswordException(String message) {
        super(message);
    }
}
