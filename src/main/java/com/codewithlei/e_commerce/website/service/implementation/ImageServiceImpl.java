package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.service.ImageLocalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class ImageServiceImpl implements ImageLocalService {
    @Value("${app.upload.dir}")
    String uploads;

    @Override
    public String imageUpload(MultipartFile file)throws IOException {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploads , filename);
        Files.copy(file.getInputStream() ,  path);
        return "/uploads/" + filename;
    }
}