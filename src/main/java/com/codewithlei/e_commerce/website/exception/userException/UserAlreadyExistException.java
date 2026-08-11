package com.codewithlei.e_commerce.website.exception.userException;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
    public UserAlreadyExistException() {
        super("User already exist exception");
    }
}
