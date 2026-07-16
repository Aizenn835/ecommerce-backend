package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.dto.cart.ResponseCartDTO;
import com.codewithlei.e_commerce.website.model.entity.FavoriteEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity , Long > {
    boolean existsByUser_EmailAndProduct_Id(String userEmail, Long productId);
    @Transactional
    void deleteByUser_EmailAndProduct_Id(String userEmail, Long productId);
    List<FavoriteEntity> findByUser(UserEntity user);
    Optional<FavoriteEntity> findByUser_EmailAndProduct_Id(String userEmail, Long productId);

}
