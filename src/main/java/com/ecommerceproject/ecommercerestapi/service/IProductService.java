package com.ecommerceproject.ecommercerestapi.service;

import com.ecommerceproject.ecommercerestapi.model.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);
    ProductDTO updatePostById(Long id, ProductDTO productDTO);
    String deleteById(Long id);
    List<ProductDTO> getCategoriesByCategoryId(Long categoryId);
}
