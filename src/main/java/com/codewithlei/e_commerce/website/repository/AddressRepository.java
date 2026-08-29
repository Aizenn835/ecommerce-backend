package com.codewithlei.e_commerce.website.repository;

import com.codewithlei.e_commerce.website.model.entity.AddressEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity , Long > {
    List<AddressEntity> findByUserOrderByIdDesc(UserEntity user);
    boolean existsByUser_IdAndFullNameAndCityAndZipCodeAndStreetAndState(Long userId,
                                                                 String fullName,
                                                                 String city,
                                                                 String zipCode,
                                                                 String street,
                                                                 String state);
    @Modifying
    @Transactional
    @Query("UPDATE AddressEntity a SET a.isDefault = false WHERE a.user = :user AND a.isDefault = true")
    void clearDefaultAddress(@Param("user")UserEntity user);

    Optional<AddressEntity> findByUserAndIsDefault(UserEntity user, Boolean isDefault);

    @Transactional
    void deleteByUserAndId(UserEntity user, Long id);


}
