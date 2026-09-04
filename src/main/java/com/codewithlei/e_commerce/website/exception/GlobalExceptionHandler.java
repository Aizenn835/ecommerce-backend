package com.codewithlei.e_commerce.website.exception;

import com.codewithlei.e_commerce.website.exception.addressException.AddressAlreadyExistException;
import com.codewithlei.e_commerce.website.exception.addressException.AddressNotFoundException;
import com.codewithlei.e_commerce.website.exception.cartException.CartAlreadyExistException;
import com.codewithlei.e_commerce.website.exception.cartException.CartEmptyException;
import com.codewithlei.e_commerce.website.exception.cartException.CartNotFoundException;
import com.codewithlei.e_commerce.website.exception.cartException.InvalidShippingFeeException;
import com.codewithlei.e_commerce.website.exception.favoriteException.FavoriteNotFoundException;
import com.codewithlei.e_commerce.website.exception.orderHistoryException.OrderAlreadyExistException;
import com.codewithlei.e_commerce.website.exception.passwordResetTokenException.InvalidPasswordException;
import com.codewithlei.e_commerce.website.exception.passwordResetTokenException.InvalidResetRequestException;
import com.codewithlei.e_commerce.website.exception.passwordResetTokenException.SamePasswordException;
import com.codewithlei.e_commerce.website.exception.paymentException.PaymentChoiceInvalidException;
import com.codewithlei.e_commerce.website.exception.paymentException.PaymentMethodAlreadyExistException;
import com.codewithlei.e_commerce.website.exception.productException.ProductNotFoundException;
import com.codewithlei.e_commerce.website.exception.userException.UserAlreadyExistException;
import com.codewithlei.e_commerce.website.exception.userException.UserEmailUnavailableException;
import com.codewithlei.e_commerce.website.exception.userException.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @ExceptionHandler(InvalidResetRequestException.class)
    public ResponseEntity<?> handleCartNotFound(InvalidResetRequestException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(400)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
    @ExceptionHandler(UserEmailUnavailableException.class)
    public ResponseEntity<?> handleEmailAlreadyExist(UserEmailUnavailableException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(409)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }
    @ExceptionHandler(OrderAlreadyExistException.class)
    public ResponseEntity<?> handleEmailAlreadyExist(OrderAlreadyExistException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(409)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }
    @ExceptionHandler(CartEmptyException.class)
    public ResponseEntity<?> handleCartNotFound(CartEmptyException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(404)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> handleInvalidPasswordRequest(InvalidPasswordException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(400)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<?> handleInvalidPasswordRequest(SamePasswordException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(400)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
    @ExceptionHandler(InvalidShippingFeeException.class)
    public ResponseEntity<?> handleInvalidPasswordRequest(InvalidShippingFeeException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(400)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
    @ExceptionHandler(PaymentMethodAlreadyExistException.class)
    public ResponseEntity<?> handlePaymentAlreadyExist(PaymentMethodAlreadyExistException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(409)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }
    @ExceptionHandler(AddressAlreadyExistException.class)
    public ResponseEntity<?> handleAddressAlreadyExist(AddressAlreadyExistException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(409)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<?> handleAddressDoesNotExist(AddressNotFoundException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(404)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(PaymentChoiceInvalidException.class)
    public ResponseEntity<?> handleInvalidPaymentRequest(PaymentChoiceInvalidException e){
        ErrorResponse error = ErrorResponse.builder()
                .status(400)
                .response(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}
