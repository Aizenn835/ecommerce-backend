package com.codewithlei.e_commerce.website.model.entity.payment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "ewallet")
@DiscriminatorValue(value = "EWALLET")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class EWalletEntity extends PaymentMethodEntity{
    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String walletIdentifier;
}
