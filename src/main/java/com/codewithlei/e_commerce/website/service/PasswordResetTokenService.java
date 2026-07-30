package com.codewithlei.e_commerce.website.service;



public interface PasswordResetTokenService {
    void resetPasswordEmail(String email);
    boolean verifyPasswordToken(String email , int token);
}
