package com.codewithlei.e_commerce.website.dto.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class RequestAddressDTO {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
