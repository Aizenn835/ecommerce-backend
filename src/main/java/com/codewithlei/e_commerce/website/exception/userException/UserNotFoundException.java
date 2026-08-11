package com.codewithlei.e_commerce.website.exception.userException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException() {
        super("User not found 404");

    }

}
