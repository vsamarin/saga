package ru.otus.homework.api.orders.dto;

import lombok.Getter;
import lombok.Setter;
import ru.otus.homework.api.orders.entity.OrderEntity;

import java.util.UUID;

@Getter
@Setter
public class Order {
    private UUID id;
    private UUID userId;
    private Long price;
    private UUID product;
    private String address;
    private OrderEntity.Status status;
}
