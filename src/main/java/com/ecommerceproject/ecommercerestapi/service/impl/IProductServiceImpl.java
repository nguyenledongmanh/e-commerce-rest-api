package com.ecommerceproject.ecommercerestapi.service.impl;

import com.ecommerceproject.ecommercerestapi.exception.ResourceNotFoundException;
import com.ecommerceproject.ecommercerestapi.model.dto.ProductDTO;
import com.ecommerceproject.ecommercerestapi.model.entity.Product;
import com.ecommerceproject.ecommercerestapi.repository.IProductRepository;
import com.ecommerceproject.ecommercerestapi.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ProductDTO> getAllProducts() {
        return iProductRepository.findAll()
                                 .stream()
                                 .map(product -> modelMapper.map(product, ProductDTO.class))
                                 .toList();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = findProduct(id);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updatePostById(Long id, ProductDTO productDTO) {
        Product product = findProduct(id);
        product.setDesc(productDTO.getDesc());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setSku(productDTO.getSku());
        Product updatedProduct = iProductRepository.save(product);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public String deleteById(Long id) {
        iProductRepository.deleteById(id);
        return "Successfully Deleted";
    }

    private Product findProduct(Long id) {
        return iProductRepository.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));
    }
}
