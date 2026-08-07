package com.codewithlei.e_commerce.website.mapper;

import com.codewithlei.e_commerce.website.dto.user.RequestUserDTO;
import com.codewithlei.e_commerce.website.dto.user.updateViewUser.ResponseUpdateUserDTO;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserEntity mapToEntity(RequestUserDTO dto){
        return UserEntity.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .password(dto.getPassword())
                .oauthAccount(false)
                .role(Roles.USER)
                .build();
    }

    public static ResponseUpdateUserDTO mapToDTO(UserEntity user){
        return ResponseUpdateUserDTO.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .number(user.getNumber())
                .build();
    }

}
