package com.codewithlei.e_commerce.website.dto.user.updateViewUser;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUpdateUserDTO {
    @NotBlank(message = "Firstname is required")
    private String firstname;
    @NotBlank(message = "Lastname is required")
    private String lastname;
    @Email
    private String email;
    @NotNull(message = "Please enter 12 digit mobile number")
    private Long number;
}
