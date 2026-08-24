package com.codewithlei.e_commerce.website.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String provider;
    @Column(nullable = false , name = "payment_token")
    private String paymentMethodToken;
    @Column(nullable = false , name = "card_brand")
    private String cardBrand;
    @Column(nullable = false , name = "last_four_digits")
    private String lastFourDigits;
    @Column(nullable = false , name = "expiry_month")
    private String expiryMonth;
    @Column(nullable = false , name = "expiry_year")
    private String expiryYear;
    @Column(nullable = false , name = "is_default")
    private Boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user ;
}
