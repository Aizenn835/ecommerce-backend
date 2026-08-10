package com.codewithlei.e_commerce.website.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ResponseTotalPriceDTO {
    private BigDecimal totalPrice;
}
