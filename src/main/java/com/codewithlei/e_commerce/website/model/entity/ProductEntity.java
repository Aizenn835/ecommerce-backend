package com.codewithlei.e_commerce.website.model.entity;

import com.codewithlei.e_commerce.website.model.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;


@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "A product should have a name!")
    private String productName;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Description is blank!")
    private String productDescription; // add text size
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotBlank(message = "A product should have a color choice!")
    private String color;
    @NotNull
    private int numberOfSold;
    @NotNull
    private int stock;
    @NotBlank
    private String productSize;
    @NotBlank
    private String imgUrl;

}
