package com.codewithlei.e_commerce.website.dto.payment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestPaymentDTO {
    @NotBlank
    private String paymentChoice; // card, e-wallet
    private Boolean isDefault;
    // Card
    @NotBlank
    private String cardHolderName;
    @NotBlank
    private String lastFourNumber;
    @NotBlank
    private Integer expiry;
    @NotBlank
    private String cardBrand;

    // E-wallet
    @NotBlank
    private String provider;
    @NotBlank
    private String walletIdentifier;
}
