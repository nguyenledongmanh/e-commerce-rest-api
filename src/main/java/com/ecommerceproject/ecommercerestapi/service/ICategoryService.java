package com.ecommerceproject.ecommercerestapi.service;

import com.ecommerceproject.ecommercerestapi.model.dto.CategoryDTO;
import com.ecommerceproject.ecommercerestapi.model.payload.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);

    CategoryDTO getCategoryById(Long id);

    CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO);

    String deleteCategoryById(Long id);
}
