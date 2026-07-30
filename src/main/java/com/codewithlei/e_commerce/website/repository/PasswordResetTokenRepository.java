package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.PasswordResetTokenEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity , Long> {
    @Transactional
    void deleteByUser(UserEntity user);
    Optional<PasswordResetTokenEntity> findTopByUserAndUsedFalseOrderByCreatedAtDesc(UserEntity user);
}
