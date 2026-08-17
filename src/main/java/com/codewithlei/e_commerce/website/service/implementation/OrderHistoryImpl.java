package com.codewithlei.e_commerce.website.service.implementation;


import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseHistoryDTO;
import com.codewithlei.e_commerce.website.exception.userException.UserNotFoundException;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.OrderHistoryRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.OrderHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderHistoryImpl implements OrderHistoryService {
    private final UserRepository userRepository;
    private final OrderHistoryRepository orderHistoryRepository;


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
