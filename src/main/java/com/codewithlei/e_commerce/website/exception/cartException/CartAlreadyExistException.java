package com.codewithlei.e_commerce.website.exception.cartException;

public class CartAlreadyExistException extends RuntimeException {
    public CartAlreadyExistException(String message) {
        super(message);
    }
    public CartAlreadyExistException() {
        super("User already have the product! ");
    }
}
