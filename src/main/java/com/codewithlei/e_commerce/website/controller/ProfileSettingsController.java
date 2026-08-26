package com.codewithlei.e_commerce.website.controller;

import com.codewithlei.e_commerce.website.dto.address.RequestAddressDTO;
import com.codewithlei.e_commerce.website.dto.address.ResponseAddressDTO;
import com.codewithlei.e_commerce.website.dto.payment.RequestPaymentDTO;
import com.codewithlei.e_commerce.website.dto.payment.ResponsePaymentDTO;
import com.codewithlei.e_commerce.website.dto.user.ResponseViewUserInformationDTO;
import com.codewithlei.e_commerce.website.dto.user.updatePasswordUser.RequestNewPasswordDTO;
import com.codewithlei.e_commerce.website.dto.user.updateViewUser.RequestUpdateUserDTO;
import com.codewithlei.e_commerce.website.dto.user.updateViewUser.ResponseUpdatePfpDTO;
import com.codewithlei.e_commerce.website.service.AddressService;
import com.codewithlei.e_commerce.website.service.PaymentService;
import com.codewithlei.e_commerce.website.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/settings")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProfileSettingsController {

    private final UserService userService;
    private final AddressService addressService;
    private final PaymentService paymentService;

    @PutMapping("/profile")
    public ResponseEntity<Map<String , String>> updateProfile(Authentication authentication ,
                                                              @RequestBody @Valid RequestUpdateUserDTO request){
        userService.updateInfo(authentication.getName() , request);
        return ResponseEntity.ok(Map.of("message" , "Successfully Updated"));
    }
    @GetMapping("/view-profile")
    public ResponseViewUserInformationDTO viewProfileInfo(Authentication authentication){
        return userService.viewUserInfo(authentication.getName());
    }
    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(Authentication authentication ,
                                               @RequestBody RequestNewPasswordDTO request){
        userService.changePassword(authentication.getName() , request);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(path =  "/change-pfp"  , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseUpdatePfpDTO> changePfp(Authentication authentication ,
                                                          @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(userService.changePfp(authentication.getName() , file));
    }
    @GetMapping("/view-address")
    public List<ResponseAddressDTO> viewUserAddress(Authentication authentication){
        String user = authentication.getName();
        return addressService.showAllAddress(user);
    }
    @PostMapping("/add-address")
    public ResponseEntity<Map<String , String>> addAddress(Authentication authentication ,
                                                         @RequestBody RequestAddressDTO request) {
        String user = authentication.getName();
        addressService.addAddress(user , request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message" , "Saved!"));
    }
    @PostMapping("/add-payment")
    public ResponseEntity<ResponsePaymentDTO> addPayment(Authentication authentication ,
                                                         @RequestBody @Valid RequestPaymentDTO request){
        String user = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.addPaymentMethod(user , request));
    }

}
