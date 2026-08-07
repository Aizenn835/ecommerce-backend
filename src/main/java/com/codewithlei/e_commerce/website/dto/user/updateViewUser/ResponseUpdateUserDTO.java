package com.codewithlei.e_commerce.website.dto.user.updateViewUser;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUpdateUserDTO {
    private String firstname;
    private String lastname;
    private String email;
    private Long number;
}
