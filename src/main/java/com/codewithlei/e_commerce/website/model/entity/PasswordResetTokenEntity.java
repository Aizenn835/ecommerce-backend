package com.codewithlei.e_commerce.website.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_Reset")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordResetTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , length = 4)
    private int resetCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private Boolean used;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @Column(nullable = false , updatable = false)
    private LocalDateTime createdAt;

}
