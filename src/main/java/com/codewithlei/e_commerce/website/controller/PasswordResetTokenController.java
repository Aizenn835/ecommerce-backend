package com.codewithlei.e_commerce.website.controller;

import com.codewithlei.e_commerce.website.dto.passwordResetToken.RequestPasswordToken;
import com.codewithlei.e_commerce.website.dto.passwordResetToken.VerifyCodeRequest;
import com.codewithlei.e_commerce.website.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PasswordResetTokenController {
    private final PasswordResetTokenService passwordResetTokenService;

    @PostMapping("/reset")
    public ResponseEntity<Map<String , String>> resetPassword(@RequestBody RequestPasswordToken request){
        passwordResetTokenService.resetPasswordEmail(request.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message" , "If that email exists, a reset code has been sent!"));
    }
    @PostMapping("/verify-code")
    public ResponseEntity<Map<String , String>> verifyCode(@RequestBody VerifyCodeRequest verifyRequest){
        boolean isValid =  passwordResetTokenService.verifyPasswordToken(verifyRequest.getEmail() , verifyRequest.getCode());
            if(!isValid){
                return ResponseEntity.badRequest().body(Map.of("message" , "Invalid or expired code."));
            }
            return ResponseEntity.ok(Map.of("message" , "Code verified successfully."));
    }

}
