package com.codewithlei.e_commerce.website.dto.payment;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponsePaymentDTO {
    private Long id;

    // payment method
    private String providerName;
    private String lastFourDigits;
    private String expiry;
    private String walletIdentifier;
    private LocalDateTime createdAt;

    private Boolean isDefault;
}
