package com.codewithlei.e_commerce.website.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseViewUserInformationDTO {
    public String fullName;
    public String email;
    public String pfpUrl;
}
