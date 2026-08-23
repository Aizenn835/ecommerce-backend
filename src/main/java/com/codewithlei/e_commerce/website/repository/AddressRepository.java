package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity , Long > {
}
