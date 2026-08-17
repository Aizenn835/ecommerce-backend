package com.codewithlei.e_commerce.website.exception.cartException;

public class InvalidShippingFeeException extends RuntimeException {
    public InvalidShippingFeeException(String message) {
        super(message);
    }
    public InvalidShippingFeeException() {
        super("Invalid shipping fee choice");
    }
}
