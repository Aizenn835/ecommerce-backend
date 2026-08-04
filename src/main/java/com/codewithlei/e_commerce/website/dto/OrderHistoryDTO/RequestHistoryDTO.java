package com.codewithlei.e_commerce.website.dto.OrderHistoryDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestHistoryDTO {
    @NotNull(message = "Product id is required")
    private Long productId;

    @Min(value = 1 , message = "Quantity must be at least 1")
    private int quantity;


}
