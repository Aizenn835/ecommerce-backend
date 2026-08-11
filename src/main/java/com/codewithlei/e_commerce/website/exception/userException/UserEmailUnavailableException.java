package com.codewithlei.e_commerce.website.exception.userException;

public class UserEmailUnavailableException extends RuntimeException {
    public UserEmailUnavailableException(String message) {
        super(message);
    }
    public UserEmailUnavailableException() {
        super("Email already exist exception");
    }
}
