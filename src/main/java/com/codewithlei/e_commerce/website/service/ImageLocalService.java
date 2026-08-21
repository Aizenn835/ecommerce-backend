package com.codewithlei.e_commerce.website.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageLocalService {
    String imageUpload(MultipartFile file , String subDirectory)throws IOException;
    void deleteImage(String imageUrl) throws IOException;
}
