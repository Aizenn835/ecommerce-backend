package com.codewithlei.e_commerce.website.exception;

import com.codewithlei.e_commerce.website.exception.CartException.CartAlreadyExistException;
import com.codewithlei.e_commerce.website.exception.CartException.CartNotFoundException;
import com.codewithlei.e_commerce.website.exception.FavoriteException.FavoriteNotFoundException;
import com.codewithlei.e_commerce.website.exception.ProductException.ProductNotFoundException;
import com.codewithlei.e_commerce.website.exception.UserException.UserAlreadyExistException;
import com.codewithlei.e_commerce.website.exception.UserException.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFound(ProductNotFoundException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(404)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(404)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExist(UserAlreadyExistException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(409)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<?> handleCartNotFound(CartNotFoundException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(404)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(CartAlreadyExistException.class)
    public ResponseEntity<?> handleCartAlreadyExist(CartAlreadyExistException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(409)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }
    @ExceptionHandler(FavoriteNotFoundException.class)
    public ResponseEntity<?> handleCartNotFound(FavoriteNotFoundException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(404)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

}
