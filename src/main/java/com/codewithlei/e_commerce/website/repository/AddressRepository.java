package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.AddressEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity , Long > {
    List<AddressEntity> findByUser(UserEntity user);
    boolean existsByUser_IdAndFullNameAndCityAndZipCodeAndStreetAndState(Long userId,
                                                                 String fullName,
                                                                 String city,
                                                                 String zipCode,
                                                                 String street,
                                                                 String state);
}
