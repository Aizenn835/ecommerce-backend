package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.PasswordResetTokenEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity , Long> {
    void deleteByUser(UserEntity user);
}
