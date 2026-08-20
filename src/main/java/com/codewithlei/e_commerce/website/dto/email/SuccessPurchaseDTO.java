package com.codewithlei.e_commerce.website.dto.email;

import com.codewithlei.e_commerce.website.model.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuccessPurchaseDTO {
    private String username;
    private String orderNumber;
    private LocalDate orderDate;
    private LocalDate estTime;
    private BigDecimal totalPaid;
    private DeliveryStatus status;
}
