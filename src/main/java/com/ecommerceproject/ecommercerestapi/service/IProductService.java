package com.ecommerceproject.ecommercerestapi.service;

import com.ecommerceproject.ecommercerestapi.model.dto.ProductDTO;

public interface IProductService {
    ProductDTO createProduct(ProductDTO productDTO);
}
