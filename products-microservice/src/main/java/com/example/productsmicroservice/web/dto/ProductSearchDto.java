package com.example.productsmicroservice.web.dto;

import com.example.productsmicroservice.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchDto {

    private Integer total;

    private Set<Product> products;
}
