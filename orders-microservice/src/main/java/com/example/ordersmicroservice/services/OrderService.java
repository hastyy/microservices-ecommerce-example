package com.example.ordersmicroservice.services;

import com.example.ordersmicroservice.entities.Order;
import com.example.ordersmicroservice.web.dtos.CreateOrderDto;
import com.example.ordersmicroservice.web.dtos.OrderDto;
import com.example.ordersmicroservice.web.exceptions.DuplicateOrderEntryException;
import com.example.ordersmicroservice.web.exceptions.ProductNotFoundException;

public interface OrderService {

    Order createOrder(String userId, CreateOrderDto createOrderDto)
            throws ProductNotFoundException, DuplicateOrderEntryException;;

}
