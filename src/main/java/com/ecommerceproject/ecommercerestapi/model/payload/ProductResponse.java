package com.ecommerceproject.ecommercerestapi.model.payload;

import com.ecommerceproject.ecommercerestapi.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private List<ProductDTO> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;
}
