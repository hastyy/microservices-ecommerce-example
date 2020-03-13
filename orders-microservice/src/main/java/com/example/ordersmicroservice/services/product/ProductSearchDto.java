package com.example.ordersmicroservice.services.product;

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
