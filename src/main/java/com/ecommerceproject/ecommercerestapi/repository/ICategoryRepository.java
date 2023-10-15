package com.ecommerceproject.ecommercerestapi.repository;

import com.ecommerceproject.ecommercerestapi.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
