package com.codewithlei.e_commerce.website.service;

public interface EmailService {
    void sendRegisterNotification(String email , String username );
    void sendPasswordTokenNotification( String email , String username, int token);
}
