package com.codewithlei.e_commerce.website.exception.orderHistoryException;

public class OrderAlreadyExistException extends RuntimeException {
    public OrderAlreadyExistException(String message) {
        super(message);
    }
}
