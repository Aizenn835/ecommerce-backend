package com.codewithlei.e_commerce.website.exception.PasswordResetTokenException;

public class CodeAlreadyExpiredException extends RuntimeException {
    public CodeAlreadyExpiredException(String message) {
        super(message);
    }
    public CodeAlreadyExpiredException() {
        super("Code is already expired generate a new one");
    }
}
