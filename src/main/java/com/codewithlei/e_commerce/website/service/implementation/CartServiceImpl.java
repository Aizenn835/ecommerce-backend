package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.cart.ResponseCartDTO;
import com.codewithlei.e_commerce.website.dto.cart.ResponseTotalPriceDTO;
import com.codewithlei.e_commerce.website.dto.email.SuccessPurchaseDTO;
import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.OrderItemResponse;
import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseOrderConfirmationDTO;
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
import com.codewithlei.e_commerce.website.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final SecureRandom secureRandom = new SecureRandom();
    private final OrderHistoryRepository orderHistoryRepository;
    private final EmailService emailService;


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
    public ResponseOrderConfirmationDTO purchaseCart(String email){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        List<CartEntity> cartItems = cartRepository.findByUser(user);
        if(cartItems.isEmpty()){
            throw new CartEmptyException("User cart is empty");
        }
        String orderNumber = "ORD-" + generateCode();
        LocalDate orderDate = LocalDate.now();
        List<OrderItemResponse> itemResponses = cartItems
                .stream()
                .map(cart -> {
                    String orderId = "SWC-" + System.currentTimeMillis();
                    BigDecimal lineTotal = cart.getProduct().getPrice()
                                    .multiply(BigDecimal.valueOf(cart.getQuantity()));
                    OrderHistoryEntity order = OrderHistoryEntity.builder()
                            .orderNumber(orderNumber)
                            .product(cart.getProduct())
                            .user(cart.getUser())
                            .orderId(orderId)
                            .orderDate(orderDate)
                            .totalPrice(lineTotal)
                            .status(DeliveryStatus.PROCESSING)
                            .orderCount(cart.getQuantity())
                            .build();

                    orderHistoryRepository.save(order);

                    return OrderItemResponse.builder()
                            .imgUrl(cart.getProduct().getImgUrl())
                            .productName(cart.getProduct().getProductName())
                            .quantity(cart.getQuantity())
                            .productPrice(lineTotal)
                            .build();
                }).toList();

        BigDecimal totalPaid = itemResponses.stream()
                        .map(OrderItemResponse::getProductPrice)
                        .reduce(BigDecimal.ZERO , BigDecimal::add);

        cartRepository.deleteAll(cartItems);

        SuccessPurchaseDTO info = SuccessPurchaseDTO.builder()
                .username(user.getUsername())
                .orderNumber(orderNumber)
                .orderDate(LocalDate.now())
                .estTime(orderDate.plusDays(5))
                .totalPaid(totalPaid)
                .status(DeliveryStatus.PROCESSING)
                .build();

        emailService.sendPurchaseSuccess(user.getEmail() , info);

        return ResponseOrderConfirmationDTO.builder()
                .orderNumber(orderNumber)
                .estTime(orderDate.plusDays(5))
                .totalPaid(totalPaid)
                .status(DeliveryStatus.PROCESSING)
                .orderItems(itemResponses)
                .build();

    }
    public int generateCode(){
        return 1000 + secureRandom.nextInt(9000);
    }




}
