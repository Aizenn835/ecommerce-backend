package com.codewithlei.e_commerce.website.dto.orderHistoryDTO;

import com.codewithlei.e_commerce.website.model.enums.DeliveryStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseHistoryDTO {
    private Long id;
    private String orderId;
    private String productImage;
    private LocalDate orderTime;
    private Integer quantity;
    private BigDecimal totalPrice;
    private DeliveryStatus status;
}
