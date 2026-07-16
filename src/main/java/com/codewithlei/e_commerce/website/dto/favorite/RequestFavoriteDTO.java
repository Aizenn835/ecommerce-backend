package com.codewithlei.e_commerce.website.dto.favorite;

import lombok.Data;

@Data
public class RequestFavoriteDTO {
    private String email;
    private Long productId;
}
