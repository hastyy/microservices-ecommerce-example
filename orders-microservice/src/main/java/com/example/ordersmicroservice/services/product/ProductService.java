package com.example.ordersmicroservice.services.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient("products")
public interface ProductService {

    @GetMapping("products/search")
    ProductSearchDto search(@RequestParam Set<Long> ids);

}
