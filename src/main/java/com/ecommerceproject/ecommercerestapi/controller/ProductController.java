package com.ecommerceproject.ecommercerestapi.controller;

import com.ecommerceproject.ecommercerestapi.model.dto.ProductDTO;
import com.ecommerceproject.ecommercerestapi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @PostMapping("")
    public ResponseEntity<ProductDTO> createProduct(ProductDTO productDTO) {
        ProductDTO savedProduct = iProductService.createProduct(productDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
}
