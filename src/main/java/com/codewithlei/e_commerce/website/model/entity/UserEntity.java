package com.codewithlei.e_commerce.website.model.entity;

import com.codewithlei.e_commerce.website.model.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Email is empty!")
    @Email
    private String email;
    @NotBlank(message = "Username is empty!")
    private String username;
    @NotBlank(message = "Password is required!")
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
}
