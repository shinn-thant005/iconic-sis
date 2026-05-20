package com.sis.iconic_sis.storage;

import org.springframework.web.multipart.MultipartFile;

public class CloudinaryStorageService implements  CloudStorageService {
    @Override
    public String uploadImage(MultipartFile file) {
        return "https://res.cloudinary.com/demo/image/upload/sample.jpg";
    }
}
