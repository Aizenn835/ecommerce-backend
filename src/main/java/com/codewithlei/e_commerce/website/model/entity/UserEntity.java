package com.codewithlei.e_commerce.website.model.entity;

import com.codewithlei.e_commerce.website.model.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
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
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @NotNull(message = "Password is required")
    private String password;
    @Column(name = "number", precision = 12)
    private Long number;
    @Column(nullable = false)
    private Boolean oauthAccount;
    @Column(name = "profile_picture")
    private String pfpUrl;
    @Enumerated(EnumType.STRING)
    private Roles role;
}
