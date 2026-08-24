package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.payment.RequestPaymentDTO;
import com.codewithlei.e_commerce.website.dto.payment.ResponsePaymentDTO;
import com.codewithlei.e_commerce.website.exception.userException.UserNotFoundException;
import com.codewithlei.e_commerce.website.model.entity.PaymentEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.PaymentRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.PaymentService;
import com.codewithlei.e_commerce.website.validation.PaymentValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentValidation payment;

    // this needs a real payment token later
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePaymentDTO addPaymentMethod(String email , RequestPaymentDTO request){
        UserEntity currentUser = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        payment.validate(request);

        String paymentToken = "ptk_" + UUID.randomUUID();

        PaymentEntity payment = PaymentEntity.builder()
                .provider(request.getProvider())
                .paymentMethodToken(paymentToken)
                .cardBrand(request.getCardBrand())
                .lastFourDigits(request.getLastFourDigits())
                .expiryMonth(request.getExpiryMonth())
                .expiryYear(request.getExpiryYear())
                .isDefault(request.getIsDefault())
                .user(currentUser)
                .build();

        PaymentEntity saved = paymentRepository.save(payment);
        return new ResponsePaymentDTO(saved.getLastFourDigits() ,
                                      saved.getExpiryMonth() ,
                                      saved.getExpiryYear()
        );
    }
}
