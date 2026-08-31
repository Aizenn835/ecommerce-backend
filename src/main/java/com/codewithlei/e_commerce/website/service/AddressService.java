package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.address.RequestAddressDTO;
import com.codewithlei.e_commerce.website.dto.address.ResponseAddressDTO;
import com.codewithlei.e_commerce.website.dto.address.ResponseShowFullAddressDTO;

import java.util.List;

public interface AddressService {
    void addAddress(String email , RequestAddressDTO request);
    List<ResponseAddressDTO> showAllAddress(String email);
    ResponseAddressDTO showDefaultAddress(String email);
    void deleteAddress(String email , Long id);
    ResponseShowFullAddressDTO getAddress(String email , Long id);
    void updateAddressInfo(String email , Long id , RequestAddressDTO request);


}
