package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.address.RequestAddressDTO;
import com.codewithlei.e_commerce.website.dto.address.ResponseAddressDTO;
import com.codewithlei.e_commerce.website.exception.userException.UserNotFoundException;
import com.codewithlei.e_commerce.website.model.entity.AddressEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.codewithlei.e_commerce.website.dto.address.ResponseAddressDTO.format;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final UserRepository userRepository;

   @Override
   @Transactional(rollbackFor = Exception.class)
   public ResponseAddressDTO addAddress(String email , RequestAddressDTO request){
       UserEntity user = userRepository.findByEmail(email)
               .orElseThrow(UserNotFoundException::new);

       AddressEntity address = AddressEntity.builder()
               .street(request.getStreet().trim())
               .city(request.getCity())
               .state(request.getState())
               .zipCode(request.getZipCode())
               .build();

       user.setAddress(address);
       userRepository.save(user);

       return new ResponseAddressDTO(format(address));
    }

}
