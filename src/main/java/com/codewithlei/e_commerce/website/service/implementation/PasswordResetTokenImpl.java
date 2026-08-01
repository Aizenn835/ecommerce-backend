package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.model.entity.PasswordResetTokenEntity;
import com.codewithlei.e_commerce.website.repository.PasswordResetTokenRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.EmailService;
import com.codewithlei.e_commerce.website.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class PasswordResetTokenImpl implements PasswordResetTokenService {
    private static final SecureRandom secureRandom = new SecureRandom();
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final int expiration;

    public PasswordResetTokenImpl(EmailService emailService ,
                                  UserRepository userRepository,
                                  PasswordResetTokenRepository passwordResetTokenRepository,
                                  @Value("${app.password.reset.expiration}") int expiration){
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.expiration = expiration;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    /*
    - check for some logical error here tomorrow
    - Also remove the latest reset code given to the user if the code expired
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPasswordEmail(String email){
        userRepository.findByEmail(email).ifPresent(user -> {
            passwordResetTokenRepository.deleteByUser(user);
            PasswordResetTokenEntity reset = PasswordResetTokenEntity.builder()
                    .resetCode(generateCode())
                    .user(user)
                    .email(user.getEmail())
                    .used(false)
                    .createdAt(LocalDateTime.now())
                    .expiredAt(LocalDateTime.now().plusMinutes(expiration))
                    .build();

            passwordResetTokenRepository.save(reset);
            emailService.sendPasswordTokenNotification(user.getEmail() , user.getUsername(), reset.getResetCode());
        });
    }
    @Override
    public boolean verifyPasswordToken(String email , int code){
        return userRepository.findByEmail(email)
                .flatMap(passwordResetTokenRepository::findTopByUserAndUsedFalseOrderByCreatedAtDesc)
                .filter(resetToken -> resetToken.getResetCode() == code)
                .filter(resetToken -> resetToken.getExpiredAt().isAfter(LocalDateTime.now()))
                .map(entityToken -> {
                    entityToken.setUsed(true);
                    passwordResetTokenRepository.save(entityToken);
                    return true;
                }).orElse(false);
    }
    public int generateCode(){
        return 100000 + secureRandom.nextInt(900000);
    }


}
