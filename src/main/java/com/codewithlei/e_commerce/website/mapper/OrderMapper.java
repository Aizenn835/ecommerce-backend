package com.codewithlei.e_commerce.website.mapper;

import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseHistoryDTO;
import com.codewithlei.e_commerce.website.model.entity.OrderHistoryEntity;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper {
    public ResponseHistoryDTO mapToDTO(OrderHistoryEntity order){
        return ResponseHistoryDTO.builder()
                .orderId(order.getOrderId())
                .productImage(order.getProduct().getImgUrl())
                .orderDate(order.getOrderDate())
                .quantity(order.getOrderCount())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }
}
