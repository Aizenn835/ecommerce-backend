package com.codewithlei.e_commerce.website.validation;

import com.codewithlei.e_commerce.website.dto.payment.RequestPaymentDTO;
import com.codewithlei.e_commerce.website.exception.paymentException.PaymentMethodAlreadyExistException;
import com.codewithlei.e_commerce.website.repository.CreditCardRepository;
import com.codewithlei.e_commerce.website.repository.EwalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentValidation {

    private final EwalletRepository ewalletRepository;
    private final CreditCardRepository creditCardRepository;

    public void paymentMethodAlreadyExist(Long id , RequestPaymentDTO request){
        if(ewalletRepository.existsByUser_IdAndProviderAndWalletIdentifier(id ,
                request.getProvider() ,
                request.getWalletIdentifier()) || creditCardRepository.existsByUser_IdAndCardHolderNameAndCardLastFourDigitsAndMonthAndYear(id ,
                request.getCardHolderName() , request.getCardLastFourDigits() ,
                request.getMonth() ,
                request.getYear())){

            throw new PaymentMethodAlreadyExistException();
        }
    }
}
