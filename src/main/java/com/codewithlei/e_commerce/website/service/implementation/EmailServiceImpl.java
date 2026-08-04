package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendRegisterNotification(String email , String username ){
        SimpleMailMessage mail =  new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Welcome to SwiftCart!");
        mail.setText("Hi there " + username + "!" + "\n\n" +
                "Welcome to SwiftCart! We're glad to have you with us.\n\n" +
                "Your account has been created successfully, and you're all set to start browsing " +
                "our latest collections — from everyday essentials to new arrivals.\n\n" +
                "If you ever have questions or run into any issues, feel free to reach out to our support team.\n\n" +
                "Happy shopping!\n" +
                "The SwiftCart Team");
        mailSender.send(mail);
    }
    @Override
    @Async
    public void sendPasswordTokenNotification(String email , String username , int token) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Your SwiftCart password reset code");
        mail.setText("Hi there! " + username + "\n\n" +
                "We received a request to reset your SwiftCart password.\n\n" +
                "Your verification code is: " + token + "\n\n" +
                "This code will expire in 10 minutes. If you didn't request this, " +
                "you can safely ignore this email — your password won't be changed.\n\n" +
                "The SwiftCart Team");
        mailSender.send(mail);
    }
    @Override
    @Async
    public void sendPasswordResetSuccess(String email , String username){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Your Password Has Been Changed");
        mail.setText("Hi there " + username + "!" + "\n\n" +
                "Your SwiftCart account password has been changed successfully.\n\n" +
                "This is a confirmation that your password was updated. You can now use your new password to sign in to your account.\n\n" +
                "If you did not make this change, please secure your account immediately by resetting your password or contacting our support team.\n\n" +
                "Thank you for helping us keep your account safe.\n\n" +
                "Stay secure!\n" +
                "The SwiftCart Team");
        mailSender.send(mail);
    }
}
