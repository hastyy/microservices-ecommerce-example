package com.example.productsmicroservice.web.controllers;

import com.example.productsmicroservice.services.ProductService;
import com.example.productsmicroservice.web.dto.ProductSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

    public static final String BASE_URL = "/products";

    private final ProductService productService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ProductSearchDto search(@RequestParam List<Long> ids) {

        log.info("Retrieving all products in list: {}", ids);

        ProductSearchDto searchResult = productService.search(ids);

        log.info("Found {} products", searchResult.getTotal());

        return searchResult;
    }

}
