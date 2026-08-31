package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.address.RequestAddressDTO;
import com.codewithlei.e_commerce.website.dto.address.ResponseAddressDTO;
import com.codewithlei.e_commerce.website.dto.address.ResponseShowFullAddressDTO;
import com.codewithlei.e_commerce.website.exception.addressException.AddressAlreadyExistException;
import com.codewithlei.e_commerce.website.exception.addressException.AddressNotFoundException;
import com.codewithlei.e_commerce.website.exception.userException.UserNotFoundException;
import com.codewithlei.e_commerce.website.mapper.AddressMapper;
import com.codewithlei.e_commerce.website.model.entity.AddressEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.AddressRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.AddressService;
import com.codewithlei.e_commerce.website.validation.AddressValidation;
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
    private final AddressValidation addressValidation;
    private final AddressMapper addressMapper;


   @Override
   @Transactional(rollbackFor = Exception.class)
   public void addAddress(String email , RequestAddressDTO request){
       UserEntity user = userRepository.findByEmail(email)
               .orElseThrow(UserNotFoundException::new);

       if(addressValidation.addressAlreadyExists(user.getId() , request)) {
           throw new AddressAlreadyExistException("Address Already Existed!");
       }
       // if the user address is less than equal to 1 make it the default address
       if(request.getIsDefault()){
           addressRepository.clearDefaultAddress(user);
       }
       AddressEntity address = AddressEntity.builder()
               .fullName(request.getFullName())
               .street(request.getStreet().trim())
               .city(request.getCity())
               .state(request.getState())
               .zipCode(request.getZipCode())
               .isDefault(request.getIsDefault())
               .user(user)
               .build();

       addressRepository.save(address);
    }
    @Override
    public List<ResponseAddressDTO> showAllAddress(String email){
       UserEntity user = userRepository.findByEmail(email)
               .orElseThrow(UserNotFoundException::new);

       List<AddressEntity> userAddress = addressRepository.findByUserOrderByIdDesc(user);

       return userAddress.stream()
                .map(address -> new ResponseAddressDTO(address.getId(), address.getFullName(),
                                                                    format(address) , address.getIsDefault()))
                .toList();
    }
    @Override
    public ResponseAddressDTO showDefaultAddress(String email){
       UserEntity user = userRepository.findByEmail(email)
               .orElseThrow(UserNotFoundException::new);

        AddressEntity userAddress = addressRepository.findByUserAndIsDefault(user , true)
                        .orElseThrow(AddressNotFoundException::new);
        System.out.println("Status: " + userAddress);
        return new ResponseAddressDTO(userAddress.getId(),
                                      userAddress.getFullName(),
                                      format(userAddress) ,
                                      userAddress.getIsDefault());
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAddress(String email , Long id){
      UserEntity user = userRepository.findByEmail(email)
              .orElseThrow(UserNotFoundException::new);

      addressRepository.deleteByUserAndId(user , id);
    }
    @Override
    public ResponseShowFullAddressDTO getAddress(String email , Long id){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        AddressEntity address = addressRepository.findByUserAndId(user , id)
                .orElseThrow(AddressNotFoundException::new);

        return ResponseShowFullAddressDTO.builder()
                .fullName(address.getFullName())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .build();
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void  updateAddressInfo(String email  , Long id , RequestAddressDTO request){
        UserEntity user = userRepository.findByEmail(email)
               .orElseThrow(UserNotFoundException::new);

        AddressEntity address = addressRepository.findByUserAndId(user , id)
                .orElseThrow(AddressNotFoundException::new);

        if(request.getIsDefault()){
            addressRepository.clearDefaultAddress(user);
        }

        AddressEntity update = addressMapper.mapToEntity(address , request);

        addressRepository.save(update);












    }

}
