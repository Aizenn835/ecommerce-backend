package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.address.RequestAddressDTO;
import com.codewithlei.e_commerce.website.dto.address.ResponseAddressDTO;
import com.codewithlei.e_commerce.website.exception.userException.UserNotFoundException;
import com.codewithlei.e_commerce.website.model.entity.AddressEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.AddressRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codewithlei.e_commerce.website.dto.address.ResponseAddressDTO.format;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

   @Override
   @Transactional(rollbackFor = Exception.class)
   public void addAddress(String email , RequestAddressDTO request){
       UserEntity user = userRepository.findByEmail(email)
               .orElseThrow(UserNotFoundException::new);

       AddressEntity address = AddressEntity.builder()
               .fullName(request.getFullName())
               .street(request.getStreet().trim())
               .city(request.getCity())
               .state(request.getState())
               .zipCode(request.getZipCode())
               .isDefault(false)
               .user(user)
               .build();

       addressRepository.save(address);
    }
    // This endpoint should return the full name of the user.
    // Fix this tom.
    @Override
    public List<ResponseAddressDTO> showAllAddress(String email){
       UserEntity user = userRepository.findByEmail(email)
               .orElseThrow(UserNotFoundException::new);

       List<AddressEntity> userAddress = addressRepository.findByUser(user);

       // I'll return 200 ok instead of not found(404) here if the user address is empty.

       return userAddress.stream()
                .map(address -> new ResponseAddressDTO(format(address)))
                .toList();
    }

}
