package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.cart.ResponseCartDTO;

import java.util.List;

public interface CartService {
    void addToCart(String email , Long id , int quantity);
    void addWishlistItemToCart(String email , Long id);
    void deleteToCart(String email , Long productId);
    List<ResponseCartDTO> getAllUserCart(String email);
    void updateQuantity(Long id , int quantity);
}
