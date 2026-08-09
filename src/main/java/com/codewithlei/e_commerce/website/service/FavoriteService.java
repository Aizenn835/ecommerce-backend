package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.favorite.ResponseFavoriteDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface FavoriteService {
     void toggleFavorite(String email , Long id);
     List<ResponseFavoriteDTO> getFavorite(String email);
     void deleteFavorite(String email , Long id);
     void addAllToCart(String email);
     Long countFavorite(String email);
}
