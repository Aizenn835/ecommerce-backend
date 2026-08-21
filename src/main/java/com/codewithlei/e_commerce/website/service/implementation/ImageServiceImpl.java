package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.service.ImageLocalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
public class ImageServiceImpl implements ImageLocalService {
    @Value("${app.upload.dir}")
    String uploadRoot;

    @Override
    public String imageUpload(MultipartFile file , String subDirectory)throws IOException{
        Path uploadPath = Paths.get(uploadRoot , subDirectory);
        Files.createDirectories(uploadPath);

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = uploadPath.resolve(filename);
        Files.copy(file.getInputStream() , path , StandardCopyOption.REPLACE_EXISTING);

        return "/" + uploadRoot + "/" + subDirectory + "/" + filename;
    }
    @Override
    public void deleteImage(String imageUrl) throws IOException{
        if(imageUrl == null || imageUrl.isBlank()){
            return;
        }
        String relativePath = imageUrl.startsWith("/")
                ? imageUrl.substring(1)
                : imageUrl;

        Path path = Paths.get(relativePath);
        Files.deleteIfExists(path);
    }
}