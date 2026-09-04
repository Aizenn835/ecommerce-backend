package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.payment.RequestPaymentDTO;
import com.codewithlei.e_commerce.website.dto.payment.ResponsePaymentDTO;
import com.codewithlei.e_commerce.website.exception.paymentException.PaymentChoiceInvalidException;
import com.codewithlei.e_commerce.website.exception.userException.UserNotFoundException;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.CardPaymentEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.EWalletEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.PaymentMethodEntity;
import com.codewithlei.e_commerce.website.repository.PaymentRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void addPaymentMethod(String email , RequestPaymentDTO request){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        PaymentMethodEntity payment = switch (request.getPaymentChoice()){
            case "CARD" -> CardPaymentEntity.builder()
                    .user(user)
                    .isDefault(request.getIsDefault())
                    .createdAt(LocalDateTime.now())
                    .cardHolderName(request.getCardHolderName())
                    .cardLastFourDigits(extractLastDigits(request.getLastFourNumber()))
                    .cardBrand(request.getCardBrand())
                    .expiry(request.getExpiry())
                    .build();

            case "E-WALLET" -> EWalletEntity.builder()
                    .user(user)
                    .isDefault(request.getIsDefault())
                    .createdAt(LocalDateTime.now())
                    .provider(request.getProvider())
                    .walletIdentifier(request.getWalletIdentifier())
                    .build();

            default -> throw new PaymentChoiceInvalidException();
        };

        paymentRepository.save(payment);
    }
    private String extractLastDigits(String cardNumber){
        return cardNumber.substring(12);
    }
    public ResponsePaymentDTO viewAvailablePaymentMethods(String email){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return paymentRepository.findByUser(user)
                .stream()
                .map()
    }
}
