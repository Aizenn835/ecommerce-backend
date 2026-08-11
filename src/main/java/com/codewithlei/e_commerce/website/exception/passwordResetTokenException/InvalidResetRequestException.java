package com.codewithlei.e_commerce.website.exception.passwordResetTokenException;

public class InvalidResetRequestException extends RuntimeException {
    public InvalidResetRequestException(String message) {
        super(message);
    }
    public InvalidResetRequestException() {
        super("Invalid reset request, Please try again.");
    }
}
