package com.ecommerceproject.ecommercerestapi.controller;

import com.ecommerceproject.ecommercerestapi.model.dto.CategoryDTO;
import com.ecommerceproject.ecommercerestapi.model.payload.CategoryResponse;
import com.ecommerceproject.ecommercerestapi.service.ICategoryService;
import com.ecommerceproject.ecommercerestapi.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @PostMapping("")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = iCategoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ) {
        return ResponseEntity.ok(iCategoryService.getAllCategories(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(iCategoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategoryById(@PathVariable Long id, @RequestBody CategoryDTO newCategory) {
        return ResponseEntity.ok(iCategoryService.updateCategoryById(id, newCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(iCategoryService.deleteCategoryById(id));
    }
}
