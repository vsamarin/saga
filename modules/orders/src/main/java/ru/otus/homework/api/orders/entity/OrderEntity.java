package ru.otus.homework.api.orders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "t_order")
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "product", nullable = false)
    private UUID product;

    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public enum Status {
        PROCESSING,
        FINISHED,
        CANCELLED,
    }

}
