package com.codewithlei.e_commerce.website.dto.cart;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestCartDTO {
    private Long productId;
    private int quantity;
}
