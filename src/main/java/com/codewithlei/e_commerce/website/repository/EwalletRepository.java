package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.payment.EWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EwalletRepository extends JpaRepository<EWalletEntity , Long> {
    boolean existsByUser_IdAndProviderAndWalletIdentifier(Long userId,
                                                          String provider,
                                                          String walletIdentifier);
}
