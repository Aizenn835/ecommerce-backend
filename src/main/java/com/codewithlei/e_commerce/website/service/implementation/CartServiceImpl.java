package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.cart.ResponseCartDTO;
import com.codewithlei.e_commerce.website.dto.cart.ResponseTotalPriceDTO;
import com.codewithlei.e_commerce.website.exception.cartException.CartEmptyException;
import com.codewithlei.e_commerce.website.exception.cartException.CartNotFoundException;
import com.codewithlei.e_commerce.website.exception.cartException.InvalidShippingFeeException;
import com.codewithlei.e_commerce.website.exception.productException.ProductNotFoundException;
import com.codewithlei.e_commerce.website.exception.userException.UserNotFoundException;
import com.codewithlei.e_commerce.website.mapper.CartMapper;
import com.codewithlei.e_commerce.website.model.entity.CartEntity;
import com.codewithlei.e_commerce.website.model.entity.OrderHistoryEntity;
import com.codewithlei.e_commerce.website.model.entity.ProductEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.enums.CartAction;
import com.codewithlei.e_commerce.website.model.enums.DeliveryStatus;
import com.codewithlei.e_commerce.website.repository.CartRepository;
import com.codewithlei.e_commerce.website.repository.OrderHistoryRepository;
import com.codewithlei.e_commerce.website.repository.ProductRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private final OrderHistoryRepository orderHistoryRepository;


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
    public ResponseTotalPriceDTO getTotalSummary(String email , String shippingMethod){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        List<CartEntity> userCart = cartRepository.findByUser(user);

        BigDecimal subTotal = userCart
                .stream()
                .map(cart -> cart.getProduct().getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())))
                .reduce(BigDecimal.ZERO , BigDecimal::add);

         BigDecimal shippingFee = getShippingMethod(shippingMethod);
         BigDecimal tax = subTotal.multiply(BigDecimal.valueOf(0.08));
         BigDecimal grandTotal = subTotal.add(tax).add(shippingFee);

         return new ResponseTotalPriceDTO(subTotal , shippingFee , tax , grandTotal);
    }

    private BigDecimal getShippingMethod(String shippingMethod){
        return switch(shippingMethod){
            case "standard shipping" -> BigDecimal.ZERO;
            case "express shipping" -> new BigDecimal("12.95");
            case "priority overnight" -> new BigDecimal("24.95");
            default ->throw new InvalidShippingFeeException();
        };
    }

    @Override
    public void deleteToCart(String email , Long id){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        CartEntity cart = cartRepository
                .findByUser_IdAndId(user.getId() , id)
                .orElseThrow(CartNotFoundException::new);
        cartRepository.delete(cart);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void purchaseCart(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        List<CartEntity> cart = cartRepository.findByUser(user);
        if(cart.isEmpty()) throw new CartEmptyException("Cart is empty");

        List<OrderHistoryEntity> list = new ArrayList<>();

        for(CartEntity cartList : cart){
            BigDecimal totalPrice = getTotalPrice(cartList);
            final String head = "SWC-";
            OrderHistoryEntity order = OrderHistoryEntity.builder()
                    .product(cartList.getProduct())
                    .user(cartList.getUser())
                    .orderId(head + System.currentTimeMillis())
                    .orderTime(LocalDate.now())
                    .totalPrice(totalPrice)
                    .status(DeliveryStatus.PROCESSING)
                    .orderCount(cartList.getQuantity())
                    .build();

            list.add(order);
        }
        orderHistoryRepository.saveAll(list);
        cartRepository.deleteAll(cart);
    }
    private BigDecimal getTotalPrice(CartEntity cartList){
        return cartList.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(cartList.getQuantity()));
    }

}
