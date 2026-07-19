package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.cart.ResponseCartDTO;
import com.codewithlei.e_commerce.website.exception.CartException.CartNotFoundException;
import com.codewithlei.e_commerce.website.exception.ProductException.ProductNotFoundException;
import com.codewithlei.e_commerce.website.exception.UserException.UserNotFoundException;
import com.codewithlei.e_commerce.website.mapper.CartMapper;
import com.codewithlei.e_commerce.website.model.entity.CartEntity;
import com.codewithlei.e_commerce.website.model.entity.ProductEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.CartRepository;
import com.codewithlei.e_commerce.website.repository.ProductRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Override
    public List<ResponseCartDTO> getAllUserCart(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        List<CartEntity> cartItems = cartRepository.findByUserOrderByIdAsc(user);
        return cartItems.stream()
                .map(cartMapper::mapToCartDTO)
                .toList();
    }
    @Override
    @Transactional(rollbackFor = Exception.class) // needs to check if the user already added the same product in the database
    public void addToCart(String email , Long id , int quantity){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        Optional<CartEntity> existingCart = cartRepository.findByUserAndProduct(user , product);
        if(existingCart.isPresent()){
            CartEntity cart = existingCart.get();
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }else{
            CartEntity cart = CartEntity.builder()
                    .product(product)
                    .user(user)
                    .quantity(quantity)
                    .build();
            cartRepository.save(cart);
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateQuantity( Long id , int quantity){ // Should i update the price as well?
        CartEntity cart = cartRepository.findById(id)
                .orElseThrow(CartNotFoundException::new);
        if(quantity <= 0) {
            cartRepository.delete(cart);
            return;
        }
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }
    @Override
    public void deleteToCart(String email , Long id){ // this needs to find the cart id
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        CartEntity cart = cartRepository
                .findByUser_IdAndId(user.getId() , id)
                .orElseThrow(CartNotFoundException::new);
        cartRepository.delete(cart);
    }
}
