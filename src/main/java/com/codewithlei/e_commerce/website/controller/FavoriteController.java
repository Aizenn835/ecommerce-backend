package com.codewithlei.e_commerce.website.controller;

import com.codewithlei.e_commerce.website.dto.favorite.ResponseFavoriteDTO;
import com.codewithlei.e_commerce.website.dto.product.ProductDTO;
import com.codewithlei.e_commerce.website.service.FavoriteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @GetMapping("/get-favorite")
    public List<ResponseFavoriteDTO> getFavorite(Authentication authentication){
        return favoriteService.getFavorite(authentication.getName());
    }
    @PostMapping("/add-wishlist/{id}")
    public ResponseEntity<String> toggleFavorite(Authentication authentication , @PathVariable("id") Long productId){
        favoriteService.toggleFavorite(authentication.getName() , productId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Added to wishlist!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String , String>> deleteCart(Authentication authentication , @PathVariable("id") Long productId){
        favoriteService.deleteFavorite(authentication.getName() , productId);
        return ResponseEntity.ok(Map.of("message", "Successfully deleted"));
    }


}
