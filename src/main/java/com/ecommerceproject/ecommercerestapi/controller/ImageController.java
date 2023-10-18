package com.ecommerceproject.ecommercerestapi.controller;

import com.ecommerceproject.ecommercerestapi.model.dto.ImageResponse;
import com.ecommerceproject.ecommercerestapi.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private IImageService iImageService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,
                                         @RequestParam("productId") Long productId) throws
            IOException {
        String uploadImage = iImageService.uploadImage(file, productId);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(uploadImage);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> downloadImage(@PathVariable String name) {
        byte[] data = iImageService.getImage(name);
        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.valueOf("image/png"))
                             .body(data);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getAllImagesByProductId(@PathVariable("productId") Long productId) {
        List<String> images = iImageService.getImagesByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(images);
    }
}
