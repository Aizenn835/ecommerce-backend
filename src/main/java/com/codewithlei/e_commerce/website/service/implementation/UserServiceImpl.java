package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.auth.AuthToken;
import com.codewithlei.e_commerce.website.dto.auth.LoginRequest;
import com.codewithlei.e_commerce.website.dto.user.ResponseViewUserInformationDTO;
import com.codewithlei.e_commerce.website.dto.user.updateViewUser.RequestUpdateUserDTO;
import com.codewithlei.e_commerce.website.dto.user.RequestUserDTO;
import com.codewithlei.e_commerce.website.exception.PasswordResetTokenException.InvalidResetRequestException;
import com.codewithlei.e_commerce.website.exception.UserException.UserEmailUnavailableException;
import com.codewithlei.e_commerce.website.exception.UserException.UserNotFoundException;
import com.codewithlei.e_commerce.website.mapper.UserMapper;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.PasswordResetTokenRepository;
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

import java.time.LocalDateTime;

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
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public void register(RequestUserDTO dto) {
        userValidation.validateUser(dto);
        UserEntity user = userMapper.mapToEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        emailService.sendRegisterNotification(dto.getEmail(), dto.getUsername());
    }
    @Override
    public AuthToken login(LoginRequest loginRequest){ // validation required
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
    // Do oauth account become oauthAccount(false)?
    @Override
    public void resetPassword(String email , String password){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        boolean isValidRequest = passwordResetTokenRepository
                .findTopByUserAndUsedTrueOrderByCreatedAtDesc(user)
                .filter(token -> token.getExpiredAt().isAfter(LocalDateTime.now()))
                .isPresent();

        if(!isValidRequest){
            throw new InvalidResetRequestException();
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setOauthAccount(false);
        userRepository.save(user);
        emailService.sendPasswordResetSuccess(user.getEmail() , user.getUsername());
    }
    @Override
    public void updateInfo(String email , RequestUpdateUserDTO request){
         UserEntity userDetails = userRepository.findByEmail(email)
                 .orElseThrow(UserNotFoundException::new);

         if(!userDetails.getEmail().equalsIgnoreCase(request.getEmail())){
             boolean isEmailExist = userRepository.existsByEmail(request.getEmail());
             if(isEmailExist){
                 throw new UserEmailUnavailableException();
             }
         }

         userDetails.setFirstname(request.getFirstname());
         userDetails.setLastname(request.getLastname());
         userDetails.setEmail(request.getEmail());
         userDetails.setNumber(request.getNumber());

         userRepository.save(userDetails);


    }
    @Override
    public ResponseViewUserInformationDTO viewUserInfo(String email) {
        return userRepository.findByEmail(email)
                .map(user -> new ResponseViewUserInformationDTO(
                        user.getFirstname() + " " + user.getLastname(),
                        user.getEmail()))
                .orElseThrow(UserNotFoundException::new);
    }

}
