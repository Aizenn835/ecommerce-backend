package com.codewithlei.e_commerce.website.controller;

import com.codewithlei.e_commerce.website.dto.cart.RequestCartDTO;
import com.codewithlei.e_commerce.website.dto.cart.ResponseCartDTO;
import com.codewithlei.e_commerce.website.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {
    private final CartService cartService;

    @GetMapping("/all-cart")
    public List<ResponseCartDTO> getCartList(Authentication authentication){
        return cartService.getAllUserCart(authentication.getName());
    }
    @PostMapping("/add-cart")
    public ResponseEntity<String> addToCart(Authentication authentication, @RequestBody RequestCartDTO request){
        cartService.addToCart(authentication.getName() , request.getProductId() , request.getQuantity());
        return ResponseEntity.ok("Successfully added!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToCart(Authentication authentication , @PathVariable("id") Long id){
        cartService.deleteToCart(authentication.getName() , id);
        return ResponseEntity.noContent().build();
    }
}
