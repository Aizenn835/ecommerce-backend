package com.codewithlei.e_commerce.website.validation;

import com.codewithlei.e_commerce.website.dto.address.RequestAddressDTO;
import com.codewithlei.e_commerce.website.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressValidation {
    private final AddressRepository addressRepository;

    public boolean addressAlreadyExists(Long userId , RequestAddressDTO request){
        return addressRepository.existsByUser_IdAndFullNameAndCityAndZipCodeAndStreetAndState(
                userId,
                request.getFullName(),
                request.getCity(),
                request.getZipCode(),
                request.getStreet(),
                request.getState());
    }
}
