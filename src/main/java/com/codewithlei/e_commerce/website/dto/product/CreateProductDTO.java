package com.codewithlei.e_commerce.website.dto.product;

import com.codewithlei.e_commerce.website.model.enums.Category;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductDTO {
    @NotBlank(message = "A product should have a name!")
    private String productName;
    @Column(nullable = false)
    private BigDecimal price;
    @NotBlank(message = "Description is blank!")
    private String productDescription;
    private Category category;
    @NotBlank(message = "A product should have a color choice!")
    private String color;
    @NotNull
    private int numberOfSold;
    @NotNull
    private int stock;
    @NotBlank
    private String productSize;
}
