package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.address.RequestAddressDTO;
import com.codewithlei.e_commerce.website.dto.address.ResponseAddressDTO;

public interface AddressService {
    ResponseAddressDTO addAddress(String email , RequestAddressDTO request);

}
