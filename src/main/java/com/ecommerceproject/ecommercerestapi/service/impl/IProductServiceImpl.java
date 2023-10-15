package com.ecommerceproject.ecommercerestapi.service.impl;

import com.ecommerceproject.ecommercerestapi.exception.ResourceNotFoundException;
import com.ecommerceproject.ecommercerestapi.model.dto.ProductDTO;
import com.ecommerceproject.ecommercerestapi.model.entity.Category;
import com.ecommerceproject.ecommercerestapi.model.entity.Product;
import com.ecommerceproject.ecommercerestapi.repository.ICategoryRepository;
import com.ecommerceproject.ecommercerestapi.repository.IProductRepository;
import com.ecommerceproject.ecommercerestapi.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IProductServiceImpl
        implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Long categoryId = productDTO.getCategoryId();
        Category category = iCategoryRepository.findById(categoryId)
                                               .orElseThrow(() -> new ResourceNotFoundException("Category", "id",
                                                                                                String.valueOf(
                                                                                                        categoryId)));
        product.setCategory(category);
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
        Long categoryId = productDTO.getCategoryId();
        Category category = iCategoryRepository.findById(categoryId)
                                               .orElseThrow(() -> new ResourceNotFoundException("Category", "id",
                                                                                                String.valueOf(
                                                                                                        categoryId)));
        product.setDesc(productDTO.getDesc());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setSku(productDTO.getSku());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(category);

        Product updatedProduct = iProductRepository.save(product);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public String deleteById(Long id) {
        iProductRepository.deleteById(id);
        return "Successfully Deleted";
    }

    @Override
    public List<ProductDTO> getCategoriesByCategoryId(Long categoryId) {
        Optional<Category> optional = iCategoryRepository.findById(categoryId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("Category", "id", String.valueOf(categoryId));
        return iProductRepository.findAllByCategoryId(categoryId)
                                 .stream()
                                 .map(product -> modelMapper.map(product, ProductDTO.class))
                                 .toList();
    }

    private Product findProduct(Long id) {
        return iProductRepository.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));
    }
}
