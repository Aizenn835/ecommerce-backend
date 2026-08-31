package com.codewithlei.e_commerce.website.mapper;

import com.codewithlei.e_commerce.website.dto.address.RequestAddressDTO;
import com.codewithlei.e_commerce.website.model.entity.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressEntity mapToEntity(AddressEntity address , RequestAddressDTO request){
        address.setFullName(request.getFullName());
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setZipCode(request.getZipCode());
        address.setIsDefault(request.getIsDefault());

        return address;
    }
}
