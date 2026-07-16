package com.codewithlei.e_commerce.website.mapper;

import com.codewithlei.e_commerce.website.dto.user.CreateUserDTO;
import com.codewithlei.e_commerce.website.dto.user.UserDTO;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDTO mapToDTO(UserEntity user){
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
    public UserEntity mapToEntity(CreateUserDTO dto){
        return UserEntity.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(Roles.USER)
                .build();
    }

}
