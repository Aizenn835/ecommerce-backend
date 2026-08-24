package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity , Long> {
    boolean existsByCardBrandAndLastFourDigits(String cardBrand, String lastFourDigits);
}
