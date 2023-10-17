package com.ecommerceproject.ecommercerestapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Long id;
    private String name;
    private String type;

    private Long productId;
}
