package com.codewithlei.e_commerce.website.exception.addressException;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message) {
        super(message);
    }
    public AddressNotFoundException() {
        super("Address Not Found");
    }
}
