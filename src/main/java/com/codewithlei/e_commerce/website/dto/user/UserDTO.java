package com.codewithlei.e_commerce.website.dto.user;

import com.codewithlei.e_commerce.website.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private Roles role;
}
