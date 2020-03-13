package com.example.ordersmicroservice.web.mappers;

import com.example.ordersmicroservice.entities.Order;
import com.example.ordersmicroservice.entities.OrderLine;
import com.example.ordersmicroservice.web.dtos.CreateOrderDto;
import com.example.ordersmicroservice.web.dtos.OrderLineDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    OrderMapper orderMapper = OrderMapper.INSTANCE;

    @Test
    void createOrderDtoToOrder() {
        CreateOrderDto createOrderDto = CreateOrderDto.builder()
                .products(List.of(OrderLineDto.builder()
                        .productId(1L)
                        .quantity(5)
                        .build()))
                .build();

        Order order = orderMapper.createOrderDtoToOrder(createOrderDto);

        System.out.println(order);
        order.getOrderLines().forEach(ol -> System.out.println(ol.getOrder()));
    }

    @Test
    void mapOrderLines() {
        OrderLineDto orderLineDto = OrderLineDto.builder()
                .productId(1L)
                .quantity(5)
                .build();

        Set<OrderLine> orderLine = orderMapper.mapOrderLines(List.of(orderLineDto));

        System.out.println(orderLine);
    }
}