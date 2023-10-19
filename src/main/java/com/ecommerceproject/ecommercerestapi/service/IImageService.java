package com.ecommerceproject.ecommercerestapi.service;

import com.ecommerceproject.ecommercerestapi.model.dto.ImageDTO;
import com.ecommerceproject.ecommercerestapi.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    String uploadImage(MultipartFile file, Long productId, String imageName) throws
            IOException;

    byte[] getImage(String fileName);

    String getImageName(Long productId);

}
