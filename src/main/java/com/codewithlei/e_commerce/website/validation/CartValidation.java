package com.codewithlei.e_commerce.website.validation;

import com.codewithlei.e_commerce.website.exception.CartException.CartAlreadyExistException;
import com.codewithlei.e_commerce.website.exception.CartException.CartNotFoundException;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.CartRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class CartValidation {
    private final CartRepository cartRepository;

    public void validateUserCart(UserEntity user , Long productId){
        if(cartRepository.existsByUser_IdAndProduct_Id(user.getId() , productId)){
            throw new CartAlreadyExistException();
        }
    }
}
