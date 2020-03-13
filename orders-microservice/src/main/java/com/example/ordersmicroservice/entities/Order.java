package com.example.ordersmicroservice.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "\"order\"")
@EqualsAndHashCode(exclude = {"orderLines"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date createdAt;

    @Column(nullable = false)
    private String userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderLine> orderLines;

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;

        if (orderLines != null)
            orderLines.forEach(ol -> ol.setOrder(this));
    }
}
