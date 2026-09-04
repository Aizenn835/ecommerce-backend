package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentMethodEntity, Long> {
    List<PaymentMethodEntity> findByUser(UserEntity user);
}
