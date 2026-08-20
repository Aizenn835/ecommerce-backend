package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.OrderHistoryEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity , Long > {
    List<OrderHistoryEntity> findByUserOrderByOrderDateAsc(UserEntity user);
}
