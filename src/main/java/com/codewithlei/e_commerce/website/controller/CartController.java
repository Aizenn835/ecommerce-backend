package com.codewithlei.e_commerce.website.controller;

import com.codewithlei.e_commerce.website.dto.cart.RequestCartDTO;
import com.codewithlei.e_commerce.website.dto.cart.ResponseCartDTO;
import com.codewithlei.e_commerce.website.dto.cart.ResponseTotalPriceDTO;
import com.codewithlei.e_commerce.website.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/summary")
    public ResponseTotalPriceDTO getSummary(Authentication authentication ,
                                            @RequestParam String shippingMethod){
        return cartService.getTotalSummary(authentication.getName() , shippingMethod);
    }
    @PostMapping("/add-cart")
    public ResponseEntity<Map<String , String>> addToCart(Authentication authentication, @RequestBody RequestCartDTO request){
        cartService.addToCart(authentication.getName() , request.getProductId() , request.getQuantity());
        return ResponseEntity.ok(Map.of("message","Successfully added!"));
    }
    @PostMapping("/wishlist-add/{id}")
    public ResponseEntity<Map<String , String>> addByWishlist(Authentication authentication , @PathVariable("id") Long id ){
        cartService.addWishlistItemToCart(authentication.getName() , id);
        return ResponseEntity.ok(Map.of("message" , "Successfully Added To Product "));
    }
    @PostMapping("/purchase")
    public ResponseEntity<Map<String , String>> purchaseItems(Authentication authentication){
        cartService.purchaseCart(authentication.getName());
        return ResponseEntity.ok(Map.of("message" , "Successfully purchase"));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateQuantity( @PathVariable("id")Long id , @RequestParam int quantity){
        cartService.updateQuantity( id , quantity);
        return ResponseEntity.ok("Successfully updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String , String>> deleteToCart(Authentication authentication , @PathVariable("id") Long id){
        cartService.deleteToCart(authentication.getName() , id);
        return ResponseEntity.ok(Map.of("message", "Successfully deleted"));
    }
}
