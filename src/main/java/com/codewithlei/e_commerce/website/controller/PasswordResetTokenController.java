package com.codewithlei.e_commerce.website.controller;

import com.codewithlei.e_commerce.website.dto.passwordResetToken.RequestPasswordToken;
import com.codewithlei.e_commerce.website.dto.passwordResetToken.ResponsePasswordToken;
import com.codewithlei.e_commerce.website.model.entity.PasswordResetTokenEntity;
import com.codewithlei.e_commerce.website.repository.PasswordResetTokenRepository;
import com.codewithlei.e_commerce.website.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/password")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PasswordResetTokenController {
    private final PasswordResetTokenService passwordResetTokenService;
    private final PasswordResetTokenRepository repo;

    @PostMapping("/reset")
    public ResponseEntity<ResponsePasswordToken> resetPassword(@RequestBody RequestPasswordToken request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(passwordResetTokenService.resetPassword(request.getEmail()));
    }
    @GetMapping("/get")
    public List<PasswordResetTokenEntity> getData(){
        return repo.findAll();
    }

}
