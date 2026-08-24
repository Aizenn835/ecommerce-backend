package com.codewithlei.e_commerce.website.validation;

import com.codewithlei.e_commerce.website.dto.payment.RequestPaymentDTO;
import com.codewithlei.e_commerce.website.exception.cartException.CartEmptyException;
import com.codewithlei.e_commerce.website.exception.paymentException.PaymentMethodAlreadyExistException;
import com.codewithlei.e_commerce.website.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentValidation {
    private final PaymentRepository paymentRepository;

    public void validate(RequestPaymentDTO request){
        if(paymentRepository.existsByCardBrandAndLastFourDigits(request.getCardBrand() ,
                request.getLastFourDigits())){
            throw new PaymentMethodAlreadyExistException("This payment method already exist");
        }
    }
}
