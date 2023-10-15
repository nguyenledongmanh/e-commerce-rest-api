package com.ecommerceproject.ecommercerestapi.service.impl;

import com.ecommerceproject.ecommercerestapi.model.dto.ProductDTO;
import com.ecommerceproject.ecommercerestapi.model.entity.Product;
import com.ecommerceproject.ecommercerestapi.repository.IProductRepository;
import com.ecommerceproject.ecommercerestapi.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IProductServiceImpl
        implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = iProductRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }
}
