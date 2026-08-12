package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.auth.AuthToken;
import com.codewithlei.e_commerce.website.dto.auth.LoginRequest;
import com.codewithlei.e_commerce.website.dto.user.ResponseViewUserInformationDTO;
import com.codewithlei.e_commerce.website.dto.user.updatePasswordUser.RequestNewPasswordDTO;
import com.codewithlei.e_commerce.website.dto.user.updateViewUser.RequestUpdateUserDTO;
import com.codewithlei.e_commerce.website.dto.user.RequestUserDTO;
import com.codewithlei.e_commerce.website.dto.user.updateViewUser.ResponseUpdateUserDTO;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    void register(RequestUserDTO dto);
    AuthToken login(LoginRequest loginRequest);
    void resetPassword(String email , String password);
    void updateInfo(String email , RequestUpdateUserDTO  request);
    ResponseViewUserInformationDTO viewUserInfo(String email);
    void changePassword(String email , RequestNewPasswordDTO request);
}
