package com.ecommerceproject.ecommercerestapi.repository;

import com.ecommerceproject.ecommercerestapi.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IImageRepository
        extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

    Optional<Image> findByProductId(Long productId);
}
