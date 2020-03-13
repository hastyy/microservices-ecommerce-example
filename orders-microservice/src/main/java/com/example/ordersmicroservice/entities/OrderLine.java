package com.example.ordersmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_line")
@IdClass(OrderLineId.class)
@ToString(exclude = {"order"})
public class OrderLine {

    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Id
    private Long product;

    @Column(nullable = false)
    private Integer quantity;

}
