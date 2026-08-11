package com.codewithlei.e_commerce.website.exception.favoriteException;

public class FavoriteNotFoundException extends RuntimeException {
    public FavoriteNotFoundException(String message) {
        super(message);
    }
    public FavoriteNotFoundException() {
        super("Favorite not found 404");
    }
}
