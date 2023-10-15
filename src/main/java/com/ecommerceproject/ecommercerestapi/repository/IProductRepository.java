package com.ecommerceproject.ecommercerestapi.repository;

import com.ecommerceproject.ecommercerestapi.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
