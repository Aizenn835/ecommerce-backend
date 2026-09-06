package com.codewithlei.e_commerce.website.dto.payment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestPaymentDTO {
    private Boolean isDefault;
    private String paymentType;

    // e-wallet
    private String provider;
    private String walletIdentifier;

    //card
    private String cardHolderName;
    private String cardLastFourDigits;
    private int month;
    private int year;
}
