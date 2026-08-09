package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.cart.ResponseCartDTO;
import com.codewithlei.e_commerce.website.exception.CartException.CartNotFoundException;
import com.codewithlei.e_commerce.website.exception.ProductException.ProductNotFoundException;
import com.codewithlei.e_commerce.website.exception.UserException.UserNotFoundException;
import com.codewithlei.e_commerce.website.mapper.CartMapper;
import com.codewithlei.e_commerce.website.model.entity.CartEntity;
import com.codewithlei.e_commerce.website.model.entity.ProductEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.enums.CartAction;
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
    @Transactional(rollbackFor = Exception.class)
    public void addToCart(String email, Long id, int quantity) {
        UserEntity user = getUser(email);
        ProductEntity product = getProduct(id);

        saveOrUpdateCart(user, product, quantity, CartAction.DECREASE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWishlistItemToCart(String email, Long id) {
        UserEntity user = getUser(email);
        ProductEntity product = getProduct(id);
        saveOrUpdateCart(user, product, 1, CartAction.INCREASE);
    }

    private void saveOrUpdateCart(UserEntity user, ProductEntity product, int quantity, CartAction cartAction) {

        Optional<CartEntity> existingCart = cartRepository.findByUserAndProduct(user, product);

        if(existingCart.isPresent()) {
            CartEntity cart = existingCart.get();
            if(cartAction == CartAction.INCREASE) {
                cart.setQuantity(cart.getQuantity() + quantity);
            } else {
                cart.setQuantity(quantity);
            }
            cartRepository.save(cart);

        } else {
            CartEntity cart = CartEntity.builder()
                    .user(user)
                    .product(product)
                    .quantity(quantity)
                    .build();

            cartRepository.save(cart);
        }
    }
    private UserEntity getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    private ProductEntity getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
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
