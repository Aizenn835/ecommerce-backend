package com.codewithlei.e_commerce.website.dto.user;

import com.codewithlei.e_commerce.website.model.enums.Roles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestUserDTO {
    @NotBlank(message = "Email is empty!")
    @Email
    private String email;
    @NotBlank(message = "Username is empty!")
    private String username;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank(message = "Password is required!")
    private String password;
    private Roles role;
}
