package com.example.ordersmicroservice.repositories;

import com.example.ordersmicroservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
