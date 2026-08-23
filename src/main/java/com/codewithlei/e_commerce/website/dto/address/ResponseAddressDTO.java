package com.codewithlei.e_commerce.website.dto.address;


import com.codewithlei.e_commerce.website.model.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseAddressDTO {
    private String address;

    public static String format(AddressEntity address){
        return address.getStreet() + ", " + address.getCity()
                + ", " + address.getState() + " " + address.getZipCode();
    }


}
