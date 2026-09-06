package com.codewithlei.e_commerce.website.service;


import com.codewithlei.e_commerce.website.dto.payment.RequestPaymentDTO;
import com.codewithlei.e_commerce.website.dto.payment.ResponsePaymentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentMethodService {
    void addPaymentMethods(String email , RequestPaymentDTO request);
    List<ResponsePaymentDTO> viewPaymentMethod(String email);
}
