package ru.otus.homework.api.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.otus.homework.api.orders.entity.PaymentEntity;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Payment {
    private UUID id;
    private UUID orderId;
    private UUID userId;
    private Long amount;
    private PaymentEntity.Status status;
}
