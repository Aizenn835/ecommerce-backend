package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.OrderHistoryDTO.RequestHistoryDTO;
import com.codewithlei.e_commerce.website.exception.ProductException.ProductNotFoundException;
import com.codewithlei.e_commerce.website.exception.UserException.UserNotFoundException;
import com.codewithlei.e_commerce.website.model.entity.OrderHistoryEntity;
import com.codewithlei.e_commerce.website.model.entity.ProductEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.enums.DeliveryStatus;
import com.codewithlei.e_commerce.website.repository.OrderHistoryRepository;
import com.codewithlei.e_commerce.website.repository.ProductRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.OrderHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderHistoryImpl implements OrderHistoryService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderHistoryRepository orderHistoryRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void purchaseItem(String email , RequestHistoryDTO request){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        ProductEntity product = productRepository.findById(request.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        BigDecimal totalPrice = product.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        String orderId = "SWC" + System.currentTimeMillis();

        OrderHistoryEntity order = OrderHistoryEntity.builder()
                .product(product)
                .user(user)
                .orderId(orderId)
                .orderTime(LocalDateTime.now())
                .totalPrice(totalPrice)
                .status(DeliveryStatus.PENDING)
                .orderCount(request.getQuantity())
                .build();

        orderHistoryRepository.save(order);
    }
}
