package ru.otus.homework.api.orders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.entity.CourierEntity;
import ru.otus.homework.api.orders.repository.CourierRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeliveryService {

    private final CourierRepository courierRepository;

    public CourierEntity appoint(UUID orderId) {
        return courierRepository
                .findByOrderId(null)
                .map(p -> courierRepository.save(p.orderId(orderId)))
                .orElseThrow(() -> new RuntimeException("There is no free couriers"));
    }

    public void cancel(UUID orderId) {
        courierRepository
                .findByOrderId(orderId)
                .map(p -> courierRepository.save(p.orderId(null)));
    }

}
