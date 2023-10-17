package com.ecommerceproject.ecommercerestapi.service.impl;

import com.ecommerceproject.ecommercerestapi.exception.ResourceNotFoundException;
import com.ecommerceproject.ecommercerestapi.model.entity.Image;
import com.ecommerceproject.ecommercerestapi.model.entity.Product;
import com.ecommerceproject.ecommercerestapi.repository.IImageRepository;
import com.ecommerceproject.ecommercerestapi.repository.IProductRepository;
import com.ecommerceproject.ecommercerestapi.service.IImageService;
import com.ecommerceproject.ecommercerestapi.utils.ImageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class IImageServiceImpl
        implements IImageService {

    @Autowired
    private IImageRepository iImageRepository;

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String uploadImage(MultipartFile file, Long productId) throws
            IOException {
        Product product = iProductRepository.findById(productId)
                                            .orElseThrow(() -> new ResourceNotFoundException("Product", "id",
                                                                                             String.valueOf(
                                                                                                     productId)));
        Image image = iImageRepository.save(Image.builder()
                                                 .name(file.getOriginalFilename())
                                                 .type(file.getContentType())
                                                 .imageData(ImageUtils.compressImage(file.getBytes()))
                                                 .product(product)
                                                 .build());


        return "file uploaded successfully: " + file.getOriginalFilename();
    }

    @Override
    public byte[] getImage(String name) {
        Image image = iImageRepository.findByName(name)
                                      .orElseThrow(() -> new ResourceNotFoundException("Image", "Name", name));
        return ImageUtils.decompressImage(image.getImageData());
//        return image.getImageData();
    }
    @Override
    public List<String> getImagesByProductId(Long productId) {
        Optional<Product> optional = iProductRepository.findById(productId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("Product", "id", String.valueOf(productId));
        return iImageRepository.findAllImagesByProductId(productId)
                               .stream()
                               .map(Image::getName)
                               .toList();

    }
}
