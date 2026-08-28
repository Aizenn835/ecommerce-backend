package com.codewithlei.e_commerce.website.dto.address;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class RequestAddressDTO {
    @NotBlank(message = "fullName is empty")
    private String fullName;
    @NotBlank(message = "street is required")
    private String street;
    @NotBlank(message = "city is required")
    private String city;
    @NotBlank(message = "state is required")
    private String state;
    @NotBlank(message = "zipCode is required")
    private String zipCode;
    private Boolean isDefault;

}
