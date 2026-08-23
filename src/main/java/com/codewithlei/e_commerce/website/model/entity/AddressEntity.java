package com.codewithlei.e_commerce.website.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipCode;

    @OneToOne(mappedBy = "address")
    private UserEntity user;

}
