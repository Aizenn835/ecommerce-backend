package com.codewithlei.e_commerce.website.controller;

import com.codewithlei.e_commerce.website.dto.auth.AuthToken;
import com.codewithlei.e_commerce.website.dto.auth.LoginRequest;
import com.codewithlei.e_commerce.website.dto.user.CreateUserDTO;
import com.codewithlei.e_commerce.website.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthToken> register(@RequestBody @Valid CreateUserDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.register(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthToken> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.login(loginRequest));
    }
}
