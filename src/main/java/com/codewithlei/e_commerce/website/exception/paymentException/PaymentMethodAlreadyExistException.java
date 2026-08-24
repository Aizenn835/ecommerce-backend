package com.codewithlei.e_commerce.website.exception.paymentException;

public class PaymentMethodAlreadyExistException extends RuntimeException {
    public PaymentMethodAlreadyExistException(String message) {
        super(message);
    }
    public PaymentMethodAlreadyExistException() {
        super("Payment method already exist!");
    }
}
