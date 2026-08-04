package com.codewithlei.e_commerce.website.dto.OrderHistoryDTO;

import com.codewithlei.e_commerce.website.model.enums.DeliveryStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseHistoryDTO {
    private Long id;
    private String orderId;
    private String productImage;
    private LocalDateTime orderTime;
    private Integer quantity;
    private Integer totalPrice;
    private DeliveryStatus status;

}
