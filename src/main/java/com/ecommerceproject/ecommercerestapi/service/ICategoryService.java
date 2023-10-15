package com.ecommerceproject.ecommercerestapi.service;

import com.ecommerceproject.ecommercerestapi.model.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO);

    String deleteCategoryById(Long id);
}
