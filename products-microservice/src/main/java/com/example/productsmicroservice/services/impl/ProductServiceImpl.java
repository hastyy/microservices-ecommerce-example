package com.example.productsmicroservice.services.impl;

import com.example.productsmicroservice.entities.Product;
import com.example.productsmicroservice.repositories.ProductRepository;
import com.example.productsmicroservice.services.ProductService;
import com.example.productsmicroservice.web.dto.ProductSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductSearchDto search(List<Long> ids) {
        Set<Product> products = productRepository.findAllByIdIn(ids);
        return ProductSearchDto.builder()
                .products(products)
                .total(products.size())
                .build();
    }

}
