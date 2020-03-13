package com.example.ordersmicroservice.services.impl;

import com.example.ordersmicroservice.entities.Order;
import com.example.ordersmicroservice.repositories.OrderRepository;
import com.example.ordersmicroservice.services.OrderService;
import com.example.ordersmicroservice.services.product.ProductSearchDto;
import com.example.ordersmicroservice.services.product.ProductService;
import com.example.ordersmicroservice.web.dtos.CreateOrderDto;
import com.example.ordersmicroservice.web.dtos.OrderDto;
import com.example.ordersmicroservice.web.dtos.OrderLineDto;
import com.example.ordersmicroservice.web.exceptions.DuplicateOrderEntryException;
import com.example.ordersmicroservice.web.exceptions.ProductNotFoundException;
import com.example.ordersmicroservice.web.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final ProductService productService;

    @Override
    public Order createOrder(String userId, CreateOrderDto createOrderDto)
            throws ProductNotFoundException, DuplicateOrderEntryException {

        Set<Long> productIds = createOrderDto.getProducts()
                .stream()
                .map(OrderLineDto::getProductId)
                .collect(Collectors.toSet());

        if (productIds.size() < createOrderDto.getProducts().size()) {
            throw new DuplicateOrderEntryException();
        }

        ProductSearchDto productSearch = productService.search(productIds);
        if (productSearch.getTotal() < productIds.size()) {
            throw new ProductNotFoundException();
        }

        Order orderToSave = orderMapper.createOrderDtoToOrder(createOrderDto);
        orderToSave.setUserId(userId);

        Order order = orderRepository.saveAndFlush(orderToSave);

        return order;
    }

}
