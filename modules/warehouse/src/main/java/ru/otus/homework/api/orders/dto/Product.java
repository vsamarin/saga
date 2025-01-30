package ru.otus.homework.api.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.homework.api.orders.entity.ProductEntity;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private UUID id;
    private UUID orderId;
    private String name;
    private ProductEntity.Status status;
}
