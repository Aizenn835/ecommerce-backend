package com.codewithlei.e_commerce.website.exception.paymentException;

public class PaymentNotFoundException extends  RuntimeException{
    public PaymentNotFoundException() {
        super("Payment not found 404");
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
