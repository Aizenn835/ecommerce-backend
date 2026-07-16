package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.ProductEntity;
import com.codewithlei.e_commerce.website.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity , Long> {
   List<ProductEntity> findByProductNameContainingIgnoreCase(String productName);
   List<ProductEntity> findByCategory(Category category);
}
