package com.ecommerceproject.ecommercerestapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String desc;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<ProductDTO> products;
}
