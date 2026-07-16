package com.codewithlei.e_commerce.website.mapper;

import com.codewithlei.e_commerce.website.dto.cart.ResponseCartDTO;
import com.codewithlei.e_commerce.website.model.entity.CartEntity;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public ResponseCartDTO mapToCartDTO(CartEntity cart){
        return ResponseCartDTO.builder()
                .id(cart.getId())
                .productId(cart.getProduct().getId())
                .productName(cart.getProduct().getProductName())
                .price(cart.getProduct().getPrice())
                .imgUrl(cart.getProduct().getImgUrl())
                .quantity(cart.getQuantity())
                .category(cart.getProduct().getCategory())
                .build();
    }
}
