package com.codewithlei.e_commerce.website.dto.payment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestPaymentDTO {
    @NotBlank(message = "Please Enter Payment Provider")
    private String provider;
    private String paymentMethodToken; // might use this later if i have a real payment token
    @NotBlank(message = "Please specify the card brand")
    private String cardBrand;
    @NotBlank(message = "Last four digits of the credit card is missing!")
    private String lastFourDigits;
    @NotBlank(message = "Enter expiry month")
    private String expiryMonth;
    @NotBlank(message = "Enter expiry year")
    private String expiryYear;
    private Boolean isDefault;
}
