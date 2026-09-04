package com.codewithlei.e_commerce.website.model.entity.payment;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "e_wallet")
@DiscriminatorValue("EWALLET")
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@SuperBuilder
public class EWalletEntity extends PaymentMethodEntity{
    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String walletIdentifier;

}
