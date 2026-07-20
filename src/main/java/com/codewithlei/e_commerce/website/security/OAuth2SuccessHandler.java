package com.codewithlei.e_commerce.website.security;

import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.model.enums.Roles;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final String frontendUrl;

    public OAuth2SuccessHandler(UserRepository userRepository ,
                                JwtService jwtService,
                                @Value("${app.frontend-url}") String frontendUrl){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.frontendUrl = frontendUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request ,
                                        HttpServletResponse response,
                                        Authentication authentication)throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        UserEntity user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    UserEntity newUser = UserEntity.builder()
                            .email(email)
                            .username(name)
                            .password("")
                            .OauthAccount(true)
                            .role(Roles.USER)
                            .build();
                    return userRepository.save(newUser);
                });
                String token = jwtService.generateToken(user.getEmail() , user.getRole().name());
                response.sendRedirect(frontendUrl + "/pages/home.html?token=" + token);
     }
}
