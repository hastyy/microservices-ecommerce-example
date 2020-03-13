package com.example.productsmicroservice.services;

import com.example.productsmicroservice.web.dto.ProductSearchDto;

import java.util.List;

public interface ProductService {

    ProductSearchDto search(List<Long> ids);

}
