package com.codewithlei.e_commerce.website.dto.favorite;

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
public class ResponseFavoriteDTO {
    private Long id;
    private String imgUrl;
    private Category category;
    private String productName;
    private BigDecimal price;
}
