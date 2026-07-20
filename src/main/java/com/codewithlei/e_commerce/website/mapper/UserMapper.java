package com.codewithlei.e_commerce.website.mapper;

import com.codewithlei.e_commerce.website.dto.user.CreateUserDTO;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserEntity mapToEntity(CreateUserDTO dto){
        return UserEntity.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .OauthAccount(false)
                .role(Roles.USER)
                .build();
    }

}
