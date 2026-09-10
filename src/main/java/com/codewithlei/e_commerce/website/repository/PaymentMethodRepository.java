package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.entity.payment.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity , Long> {

    List<PaymentMethodEntity> findByUserOrderByCreatedAtDesc(UserEntity user);

    @Modifying
    @Transactional
    @Query("UPDATE PaymentMethodEntity p SET p.isDefault = false WHERE p.user = :user AND p.isDefault = true")
    void updateDefaultPayment(UserEntity user);
    void deleteByUserAndId(UserEntity user, Long id);

    Optional<PaymentMethodEntity> findByUserAndId(UserEntity user, Long id);

    Optional<PaymentMethodEntity> findByUserAndIsDefault(UserEntity user, Boolean isDefault);

}
