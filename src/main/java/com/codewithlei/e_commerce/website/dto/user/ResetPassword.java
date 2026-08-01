package com.codewithlei.e_commerce.website.dto.user;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPassword {
    @Email
    private String email;
    private String password;
}
