package com.codewithlei.e_commerce.website.dto.auth;

import com.codewithlei.e_commerce.website.model.enums.Roles;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private Roles roles;
}
