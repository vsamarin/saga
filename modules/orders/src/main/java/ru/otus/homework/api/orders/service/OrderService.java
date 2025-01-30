package ru.otus.homework.api.orders.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.api.orders.entity.OrderEntity;
import ru.otus.homework.api.orders.exception.NotFoundException;
import ru.otus.homework.api.orders.repository.OrderRepository;

import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderEntity create(OrderEntity entity) {
        return orderRepository.save(entity.status(OrderEntity.Status.PROCESSING));
    }

    public void cancel(UUID id) {
        orderRepository
                .findById(id)
                .map(p -> orderRepository.save(p.status(OrderEntity.Status.CANCELLED)));
    }

    public OrderEntity findById(UUID id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(format("Order %s not found.", id)));
    }

}
