package com.ecommerceproject.ecommercerestapi.controller;

import com.ecommerceproject.ecommercerestapi.model.dto.ProductDTO;
import com.ecommerceproject.ecommercerestapi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @PostMapping("")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO savedProduct = iProductService.createProduct(productDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(iProductService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(iProductService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(iProductService.updatePostById(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        return ResponseEntity.ok(iProductService.deleteById(id));
    }
}
