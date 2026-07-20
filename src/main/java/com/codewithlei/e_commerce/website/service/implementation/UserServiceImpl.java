package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.auth.AuthToken;
import com.codewithlei.e_commerce.website.dto.auth.LoginRequest;
import com.codewithlei.e_commerce.website.dto.user.CreateUserDTO;
import com.codewithlei.e_commerce.website.exception.UserException.UserNotFoundException;
import com.codewithlei.e_commerce.website.mapper.UserMapper;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.security.JwtService;
import com.codewithlei.e_commerce.website.service.EmailService;
import com.codewithlei.e_commerce.website.service.UserService;
import com.codewithlei.e_commerce.website.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserValidation userValidation;
    private final EmailService emailService;

    @Override
    public AuthToken register(CreateUserDTO dto){
        userValidation.validateUser(dto);
        UserEntity user = userMapper.mapToEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        emailService.sendRegisterNotification(dto.getEmail() , dto.getUsername() );
        String token = jwtService.generateToken(
                user.getEmail() ,
                user.getRole().name());
        return new AuthToken(token);
    }
    @Override
    public AuthToken login(LoginRequest loginRequest){
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if(user.getOauthAccount()){
            throw new RuntimeException("This account uses Google Sign-In. Please log in with Google instead.");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));
        String token = jwtService.generateToken(
                loginRequest.getEmail() ,
                user.getRole().name());
        return new AuthToken(token);
    }
}
