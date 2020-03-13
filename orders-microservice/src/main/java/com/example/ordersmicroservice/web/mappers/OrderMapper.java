package com.example.ordersmicroservice.web.mappers;

import com.example.ordersmicroservice.entities.Order;
import com.example.ordersmicroservice.entities.OrderLine;
import com.example.ordersmicroservice.web.dtos.CreateOrderDto;
import com.example.ordersmicroservice.web.dtos.OrderLineDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "products", target = "orderLines", qualifiedByName = "mapOrderLines")
    })
    Order createOrderDtoToOrder(CreateOrderDto createOrderDto);

    default Set<OrderLine> mapOrderLines(List<OrderLineDto> products) {
        return products.stream()
                .map(p -> OrderLine.builder()
                        .product(p.getProductId())
                        .quantity(p.getQuantity())
                        .build())
                .collect(Collectors.toSet());
    }

}
