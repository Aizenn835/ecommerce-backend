package com.codewithlei.e_commerce.website.exception.CartException;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message) {
        super(message);
    }
    public CartNotFoundException() {
        super("Cart not found 404");
    }
}
