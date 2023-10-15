package com.ecommerceproject.ecommercerestapi.model.dto;

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
    private String name;
    private String desc;
    private String sku;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
