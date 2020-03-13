package com.example.ordersmicroservice.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDto {

    @NotNull
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Product quantity must be higher than 0")
    private Integer quantity;

}
