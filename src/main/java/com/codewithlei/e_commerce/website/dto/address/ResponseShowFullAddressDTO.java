package com.codewithlei.e_commerce.website.dto.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseShowFullAddressDTO {
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
