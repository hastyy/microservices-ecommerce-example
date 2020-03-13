package com.example.ordersmicroservice.web.controllers;

import com.example.ordersmicroservice.entities.Order;
import com.example.ordersmicroservice.services.OrderService;
import com.example.ordersmicroservice.web.dtos.CreateOrderDto;
import com.example.ordersmicroservice.web.dtos.OrderDto;
import com.example.ordersmicroservice.web.exceptions.DuplicateOrderEntryException;
import com.example.ordersmicroservice.web.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(OrderController.BASE_URL)
public class OrderController {

    public static final String BASE_URL = "/orders";

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(Authentication authContext, @Valid @RequestBody CreateOrderDto orderDto)
            throws ProductNotFoundException, DuplicateOrderEntryException {

        String userId = ((UserDetails) authContext.getPrincipal()).getUsername();

        log.info("Creating order for user: {}", userId);

        Order order = orderService.createOrder(userId, orderDto);

        log.info("Created order: {}", order);

        return order;
    }
}
