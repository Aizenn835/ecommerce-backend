package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.email.SuccessPurchaseDTO;

public interface EmailService {
    void sendRegisterNotification(String email , String username );
    void sendPasswordTokenNotification( String email , String username, int token);
    void sendPasswordResetSuccess(String email , String username);
    void sendPurchaseSuccess(String email , SuccessPurchaseDTO request);
}
