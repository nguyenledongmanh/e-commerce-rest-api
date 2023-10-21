package com.ecommerceproject.ecommercerestapi.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    @NotEmpty(message = "Product name should not be null or empty")
    private String name;

    @NotEmpty(message = "Product description should not be null or empty")
    @Size(min = 10, message = "Product description should have at least 10 characters")
    private String desc;

    @NotEmpty(message = "Stock keeping unit should not be null or empty")
    private String sku;

    @Min(value = 0, message = "Price should greater than or equal 0")
    private double price;

    @Min(value = 0, message = "Quantity should greater than or equal 0")
    private Long quantity;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long categoryId;

    @NotEmpty(message = "Image source should not be null or empty")
    private String imgSrc;
}
