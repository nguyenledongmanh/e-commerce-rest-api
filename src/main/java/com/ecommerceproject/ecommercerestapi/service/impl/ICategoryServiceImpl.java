package com.ecommerceproject.ecommercerestapi.service.impl;

import com.ecommerceproject.ecommercerestapi.exception.ResourceNotFoundException;
import com.ecommerceproject.ecommercerestapi.model.dto.CategoryDTO;
import com.ecommerceproject.ecommercerestapi.model.entity.Category;
import com.ecommerceproject.ecommercerestapi.repository.ICategoryRepository;
import com.ecommerceproject.ecommercerestapi.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryServiceImpl
        implements ICategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = iCategoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return iCategoryRepository.findAll()
                                  .stream()
                                  .map(category -> modelMapper.map(category, CategoryDTO.class))
                                  .toList();
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = findCategory(id);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO) {
        Category category = findCategory(id);
        category.setDesc(categoryDTO.getDesc());
        category.setName(categoryDTO.getName());
        Category updatedCategory = iCategoryRepository.save(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public String deleteCategoryById(Long id) {
        iCategoryRepository.deleteById(id);
        return "Deleted Successfully";
    }

    private Category findCategory(Long id) {
        return iCategoryRepository.findById(id)
                                  .orElseThrow(
                                          () -> new ResourceNotFoundException("Category", "id", String.valueOf(id)));
    }
}
