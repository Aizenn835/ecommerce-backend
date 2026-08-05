package com.codewithlei.e_commerce.website.service.implementation;


import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseHistoryDTO;
import com.codewithlei.e_commerce.website.exception.UserException.UserNotFoundException;
import com.codewithlei.e_commerce.website.model.entity.CartEntity;
import com.codewithlei.e_commerce.website.model.entity.OrderHistoryEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.enums.DeliveryStatus;
import com.codewithlei.e_commerce.website.repository.CartRepository;
import com.codewithlei.e_commerce.website.repository.OrderHistoryRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.OrderHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderHistoryImpl implements OrderHistoryService {
    private final UserRepository userRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final CartRepository cartRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void purchaseItem(String email){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        String orderId = "SWC-" + System.currentTimeMillis();

        List<OrderHistoryEntity> orderList = new ArrayList<>();

        List<CartEntity> cartItems = cartRepository
                .findByUser_Email(user.getEmail());

        cartItems.forEach(cart -> {

            BigDecimal totalPrice = cart.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(cart.getQuantity()));

            OrderHistoryEntity order = OrderHistoryEntity.builder()
                    .product(cart.getProduct())
                    .user(cart.getUser())
                    .orderId(orderId)
                    .orderTime(LocalDate.now())
                    .totalPrice(totalPrice)
                    .status(DeliveryStatus.PROCESSING)
                    .orderCount(cart.getQuantity())
                    .build();

            orderList.add(order);
            cartRepository.deleteAll(cartItems);
        });
        orderHistoryRepository.saveAll(orderList);
    }
    @Override
    public List<ResponseHistoryDTO> getOrderHistory(String email){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return orderHistoryRepository.findByUserOrderByOrderTimeAsc(user)
                .stream()
                .map(order -> ResponseHistoryDTO.builder()
                            .id(order.getId())
                            .orderId(order.getOrderId())
                            .productImage(order.getProduct().getImgUrl())
                            .orderTime(order.getOrderTime())
                            .quantity(order.getOrderCount())
                            .totalPrice(order.getTotalPrice())
                            .status(order.getStatus())
                            .build()
                ).toList();


    }


}
