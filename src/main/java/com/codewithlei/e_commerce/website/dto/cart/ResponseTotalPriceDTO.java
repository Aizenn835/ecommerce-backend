package com.codewithlei.e_commerce.website.dto.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ResponseTotalPriceDTO {
    @NotNull
    private BigDecimal subTotal;
    @NotNull
    private BigDecimal shippingFee;
    @NotNull
    private BigDecimal tax;
    @NotNull
    private BigDecimal totalPrice;
}
