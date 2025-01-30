package ru.otus.homework.api.orders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.api.orders.entity.PaymentEntity;
import ru.otus.homework.api.orders.repository.PaymentRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentEntity create(PaymentEntity entity) {
        return paymentRepository.save(entity.id(UUID.randomUUID()).status(PaymentEntity.Status.SUCCESSFUL));
    }

    public void cancel(UUID orderId) {
        paymentRepository.findById(orderId)
                .map(p -> paymentRepository.save(p.status(PaymentEntity.Status.CANCELED)));
    }

}
