package com.codewithlei.e_commerce.website.dto.orderHistoryDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponse {
    private String imgUrl;
    private String productName;
    private int quantity;
    private BigDecimal productPrice;
}
