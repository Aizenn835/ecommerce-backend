package com.codewithlei.e_commerce.website.exception.cartException;

public class CartEmptyException extends RuntimeException {
    public CartEmptyException(String message) {
        super(message);
    }
}
