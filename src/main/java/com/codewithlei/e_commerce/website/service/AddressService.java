package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.address.RequestAddressDTO;
import com.codewithlei.e_commerce.website.dto.address.ResponseAddressDTO;

import java.util.List;

public interface AddressService {
    void addAddress(String email , RequestAddressDTO request);
    List<ResponseAddressDTO> showAllAddress(String email);

}
