package com.example.productsmicroservice.repositories;

import com.example.productsmicroservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Set<Product> findAllByIdIn(List<Long> ids);

}
