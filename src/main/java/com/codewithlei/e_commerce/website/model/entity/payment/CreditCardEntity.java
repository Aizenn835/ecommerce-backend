package com.codewithlei.e_commerce.website.model.entity.payment;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "credit_card")
@DiscriminatorValue(value = "CARD")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class CreditCardEntity extends PaymentMethodEntity{
    @Column(nullable = false)
    private String cardHolderName;

    @Column(name = "card_last_four_digits" , nullable = false)
    private String cardLastFourDigits;

    @Column(nullable = false)
    @Min(value = 1)  @Max(value = 12)
    private int month;

    @Column(nullable = false)
    @Min(value = 2026)
    private int year;


}
