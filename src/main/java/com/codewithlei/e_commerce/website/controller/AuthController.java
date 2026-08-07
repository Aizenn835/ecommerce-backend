package com.codewithlei.e_commerce.website.controller;

import com.codewithlei.e_commerce.website.dto.auth.AuthToken;
import com.codewithlei.e_commerce.website.dto.auth.LoginRequest;
import com.codewithlei.e_commerce.website.dto.user.RequestUserDTO;
import com.codewithlei.e_commerce.website.dto.user.ResetPassword;
import com.codewithlei.e_commerce.website.dto.user.ResponseViewUserInformationDTO;
import com.codewithlei.e_commerce.website.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Map<String , String>> register(@RequestBody @Valid RequestUserDTO dto){
        userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message" , "Successfully register"));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthToken> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.login(loginRequest));
    }
    @PatchMapping("/reset-password")
    public ResponseEntity<Map<String , String>> resetPassword(@RequestBody @Valid ResetPassword reset){
        userService.resetPassword(reset.getEmail()  , reset.getPassword());
        return ResponseEntity.ok(Map.of("message" , "Successfully Updated"));
    }
}
