package com.codewithlei.e_commerce.website.dto.orderHistoryDTO;

import com.codewithlei.e_commerce.website.model.enums.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOrderConfirmationDTO {
    @NotBlank
    private String orderNumber;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate estTime;
    @NotNull
    private BigDecimal totalPaid;
    private DeliveryStatus status;
    private List<OrderItemResponse> orderItems;
}
