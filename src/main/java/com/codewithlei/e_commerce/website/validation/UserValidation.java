package com.codewithlei.e_commerce.website.validation;

import com.codewithlei.e_commerce.website.dto.user.CreateUserDTO;
import com.codewithlei.e_commerce.website.exception.UserException.UserAlreadyExistException;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidation {
    private final UserRepository userRepository;

    public void validateUser(CreateUserDTO dto){
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new UserAlreadyExistException();
        }
    }
}
