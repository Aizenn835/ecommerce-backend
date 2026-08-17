package com.codewithlei.e_commerce.website.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_Id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private UserEntity user;

    @Column(nullable = false)
    private Integer quantity;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private LocalDate createdAt;




}
