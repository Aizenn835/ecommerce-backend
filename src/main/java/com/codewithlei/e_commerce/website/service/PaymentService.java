package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.payment.RequestPaymentDTO;
import com.codewithlei.e_commerce.website.dto.payment.ResponsePaymentDTO;

public interface PaymentService {
    void addPaymentMethod(String email , RequestPaymentDTO request);
    ResponsePaymentDTO viewAvailablePaymentMethods(String email);
}
