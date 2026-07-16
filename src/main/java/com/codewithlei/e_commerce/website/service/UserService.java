package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.auth.AuthToken;
import com.codewithlei.e_commerce.website.dto.auth.LoginRequest;
import com.codewithlei.e_commerce.website.dto.user.CreateUserDTO;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    AuthToken register(CreateUserDTO dto);
    AuthToken login(LoginRequest loginRequest);
}
