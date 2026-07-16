package com.codewithlei.e_commerce.website.mapper;

import com.codewithlei.e_commerce.website.dto.favorite.ResponseFavoriteDTO;
import com.codewithlei.e_commerce.website.model.entity.FavoriteEntity;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper {
    public ResponseFavoriteDTO mapToFavoriteDTO(FavoriteEntity favorite){
        return ResponseFavoriteDTO.builder()
                .id(favorite.getProduct().getId())
                .imgUrl(favorite.getProduct().getImgUrl())
                .category(favorite.getProduct().getCategory())
                .productName(favorite.getProduct().getProductName())
                .price(favorite.getProduct().getPrice())
                .build();
    }
}
