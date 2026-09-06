package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.payment.RequestPaymentDTO;
import com.codewithlei.e_commerce.website.dto.payment.ResponsePaymentDTO;
import com.codewithlei.e_commerce.website.exception.paymentException.PaymentChoiceInvalidException;
import com.codewithlei.e_commerce.website.exception.userException.UserNotFoundException;
import com.codewithlei.e_commerce.website.mapper.PaymentMapper;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.CreditCardEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.EWalletEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.PaymentMethodEntity;
import com.codewithlei.e_commerce.website.repository.PaymentMethodRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;
    private final PaymentMapper paymentMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPaymentMethods(String email , RequestPaymentDTO request){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        PaymentMethodEntity payment = switch (request.getPaymentType()){
            case "EWALLET" -> EWalletEntity.builder()
                    .user(user)
                    .createdAt(LocalDateTime.now())
                    .isDefault(request.getIsDefault())
                    .provider(request.getProvider())
                    .walletIdentifier(request.getWalletIdentifier())
                    .build();

            case "CARD"-> CreditCardEntity.builder()
                    .user(user)
                    .createdAt(LocalDateTime.now())
                    .isDefault(request.getIsDefault())
                    .cardHolderName(request.getCardHolderName())
                    .cardLastFourDigits(extractLastFourDigits(request.getCardLastFourDigits()))
                    .month(request.getMonth())
                    .year(request.getYear())
                    .build();

            default -> throw new PaymentChoiceInvalidException();
        };

        paymentMethodRepository.save(payment);
    }
    private String extractLastFourDigits(String creditCard){
        return creditCard.substring(12);
    }
    public List<ResponsePaymentDTO> viewPaymentMethod(String email){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return paymentMethodRepository.findByUser(user)
                .stream()
                .map(paymentMapper::mapToDTO)
                .toList();

    }

}
