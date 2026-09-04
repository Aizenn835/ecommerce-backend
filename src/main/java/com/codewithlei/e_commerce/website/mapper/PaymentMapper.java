package com.codewithlei.e_commerce.website.mapper;

import com.codewithlei.e_commerce.website.dto.payment.ResponsePaymentDTO;
import com.codewithlei.e_commerce.website.model.entity.payment.CardPaymentEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.PaymentMethodEntity;
import org.springframework.stereotype.Component;


@Component
public class PaymentMapper {

    // fix this tom
    public ResponsePaymentDTO mapToDTO(PaymentMethodEntity payment){
        ResponsePaymentDTO builder = ResponsePaymentDTO.builder()
                .id(payment.getId())
                .createdAt(payment.getCreatedAt())
                .isDefault(payment.getIsDefault())


    }
}
