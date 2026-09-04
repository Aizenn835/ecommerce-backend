package com.codewithlei.e_commerce.website.model.entity.payment;

import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_method")
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "payment_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Boolean isDefault;

    @JsonFormat(pattern = "2026-09-04 18:11:00")
    private LocalDateTime createdAt;
}
