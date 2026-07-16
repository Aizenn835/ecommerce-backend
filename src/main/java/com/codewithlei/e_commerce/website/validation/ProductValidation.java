package com.codewithlei.e_commerce.website.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ProductValidation {
    public void validateImg(MultipartFile file){
        if(file == null || file.isEmpty()){
            throw new IllegalArgumentException("Image is null");
        }
    }
}
