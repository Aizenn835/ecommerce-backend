package com.codewithlei.e_commerce.website.dto.user;

import com.codewithlei.e_commerce.website.model.enums.Roles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateUserDTO {
    @NotBlank(message = "Email is empty!")
    @Email
    private String email;
    @NotBlank(message = "Username is empty!")
    private String username;
    @NotBlank(message = "Password is required!")
    private String password;
    private Roles role;
}
