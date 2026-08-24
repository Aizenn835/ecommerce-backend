package com.codewithlei.e_commerce.website.dto.payment;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ResponsePaymentDTO {
    private String lastDigits;
    private String expiryMonth;
    private String expiryYear;




}
