package com.codewithlei.e_commerce.website.dto.cart;

import com.codewithlei.e_commerce.website.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCartDTO {
    private Long id;
    private Long productId;
    private String productName;
    private BigDecimal price;
    private String imgUrl;
    private int quantity;
    private Category category;
}
