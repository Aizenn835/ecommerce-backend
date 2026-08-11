package com.codewithlei.e_commerce.website.exception.productException;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
    public ProductNotFoundException(){
        super("Product Not Found 404");
    }
}
