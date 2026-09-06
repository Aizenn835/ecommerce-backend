package com.codewithlei.e_commerce.website.mapper;

import com.codewithlei.e_commerce.website.dto.payment.ResponsePaymentDTO;
import com.codewithlei.e_commerce.website.dto.payment.ResponsePaymentDTO.ResponsePaymentDTOBuilder;
import com.codewithlei.e_commerce.website.model.entity.payment.CreditCardEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.EWalletEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.PaymentMethodEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public ResponsePaymentDTO mapToDTO(PaymentMethodEntity payment){
        ResponsePaymentDTOBuilder builder = ResponsePaymentDTO.builder()
                .id(payment.getId())
                .createdAt(payment.getCreatedAt())
                .isDefault(payment.getIsDefault());

        if(payment instanceof CreditCardEntity card){
            String buildLastDigits = "••••" + card.getCardLastFourDigits();
            builder
                    .cardHolderName(card.getCardHolderName())
                    .cardLastFourDigits(buildLastDigits)
                    .month(card.getMonth())
                    .year(card.getYear());
        }else if(payment instanceof EWalletEntity ewallet){
            builder
                    .provider(ewallet.getProvider())
                    .walletIdentifier(ewallet.getWalletIdentifier());

        }
        return builder.build();
    }
}
