package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.dto.cart.ResponseCartDTO;
import com.codewithlei.e_commerce.website.model.entity.CartEntity;
import com.codewithlei.e_commerce.website.model.entity.ProductEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity , Long> {
    Optional<CartEntity> findByUser_IdAndProduct_Id(Long userId, Long productId);
    Optional<CartEntity> findByUserAndProduct(UserEntity user, ProductEntity product);
    List<CartEntity> findByUser(UserEntity user);
    Boolean existsByUser_IdAndProduct_Id(Long userId, Long productId);
}
