package com.codewithlei.e_commerce.website.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;
    private String paymentMethodToken;
    private String cardBrand;
    private String lastFourDigits;
    private String expiryMonth;
    private String expiryYear;
    private String isDefault;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private UserEntity user ;
}
