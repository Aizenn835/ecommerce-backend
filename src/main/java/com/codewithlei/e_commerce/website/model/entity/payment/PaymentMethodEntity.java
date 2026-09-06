package com.codewithlei.e_commerce.website.model.entity.payment;

import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_method")
@Getter
@DiscriminatorColumn(name = "Payment_Type")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor()
@SuperBuilder
public abstract class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @JsonFormat(pattern = "2026-09-04 18:11:00")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Boolean isDefault;

}

