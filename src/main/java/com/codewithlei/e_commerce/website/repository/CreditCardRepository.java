package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.payment.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity , Long> {
    boolean existsByUser_IdAndCardHolderNameAndCardLastFourDigitsAndMonthAndYear(Long userId,
                                                                                 String cardHolderName,
                                                                                 String cardLastFourDigits, int month, int year);
}
