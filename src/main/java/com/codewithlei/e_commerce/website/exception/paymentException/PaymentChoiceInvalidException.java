package com.codewithlei.e_commerce.website.exception.paymentException;

public class PaymentChoiceInvalidException extends RuntimeException{
    public PaymentChoiceInvalidException(String message){
        super(message);
    }
    public PaymentChoiceInvalidException(){
        super("Payment choice is invalid");
    }
}
