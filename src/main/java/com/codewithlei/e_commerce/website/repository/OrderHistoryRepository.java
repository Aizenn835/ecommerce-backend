package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity , Long > {
}
