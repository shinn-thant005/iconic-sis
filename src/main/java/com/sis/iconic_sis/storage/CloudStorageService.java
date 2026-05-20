package com.sis.iconic_sis.storage;

import org.springframework.web.multipart.MultipartFile;

public interface CloudStorageService {

    String uploadImage(MultipartFile file);
}
