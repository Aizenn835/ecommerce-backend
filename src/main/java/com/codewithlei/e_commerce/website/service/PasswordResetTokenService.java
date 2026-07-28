package com.codewithlei.e_commerce.website.service;


import com.codewithlei.e_commerce.website.dto.passwordResetToken.ResponsePasswordToken;

public interface PasswordResetTokenService {
    ResponsePasswordToken resetPassword(String email);
}
