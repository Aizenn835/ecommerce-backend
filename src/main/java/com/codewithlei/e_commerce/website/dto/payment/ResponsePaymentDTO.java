package com.codewithlei.e_commerce.website.dto.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePaymentDTO {
    //needed values:
    private Long id;
    private LocalDateTime createdAt;
    private Boolean isDefault;
    private String paymentType;

    //e-wallet needed values:
    private String provider;
    private String walletIdentifier;

    // card needed values:
    private String cardHolderName;
    private String cardLastFourDigits;
    private int month;
    private int year;

}
