package com.codewithlei.e_commerce.website.dto.product;

import com.codewithlei.e_commerce.website.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductDTO {
    private Long id;
    private String productName;
    private BigDecimal price;
    private String productDescription;
    private Category category;
    private String color;
    private int numberOfSold;
    private int stock;
    private String productSize;
    private String imgUrl;
}
