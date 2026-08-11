package com.codewithlei.e_commerce.website.validation;

import com.codewithlei.e_commerce.website.dto.user.RequestUserDTO;
import com.codewithlei.e_commerce.website.exception.userException.UserAlreadyExistException;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidation {
    private final UserRepository userRepository;

    public void validateUser(RequestUserDTO dto){
        if(userRepository.existsByEmailOrUsername(dto.getEmail() , dto.getUsername())){
            throw new UserAlreadyExistException();
        }
    }
}
