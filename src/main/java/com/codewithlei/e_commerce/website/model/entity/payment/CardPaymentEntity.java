package com.codewithlei.e_commerce.website.model.entity.payment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "card_payment")
@DiscriminatorValue("CARD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class CardPaymentEntity extends PaymentMethodEntity{

    @Column(name = "card_holder" , nullable = false)
    private String cardHolderName;

    @Column(name = "card_last_four_digits" , nullable = false)
    private String cardLastFourDigits;

    private String cardBrand;

    @Column(nullable = false)
    private Integer expiry;
}
