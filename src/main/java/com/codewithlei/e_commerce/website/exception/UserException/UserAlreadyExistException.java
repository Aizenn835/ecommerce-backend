package com.codewithlei.e_commerce.website.exception.UserException;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
    public UserAlreadyExistException() {
        super("User already exist exception");
    }
}
