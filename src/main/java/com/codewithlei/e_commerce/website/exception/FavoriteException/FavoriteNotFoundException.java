package com.codewithlei.e_commerce.website.exception.FavoriteException;

public class FavoriteNotFoundException extends RuntimeException {
    public FavoriteNotFoundException(String message) {
        super(message);
    }
    public FavoriteNotFoundException() {
        super("Favorite not found 404");
    }
}
