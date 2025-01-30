package ru.otus.homework.api.orders.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.dto.Payment;
import ru.otus.homework.api.orders.entity.PaymentEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PaymentMapper implements Mapper<Map<String, Object>, PaymentEntity> {

    @Override
    public PaymentEntity map(Map<String, Object> map) {
        PaymentEntity payment = new PaymentEntity();
        payment.orderId(UUID.fromString((String) map.get("orderId")));
        payment.userId(UUID.fromString((String) map.get("userId")));
        payment.amount(Long.valueOf(String.valueOf(map.get("amount"))));
        return payment;
    }

    public Payment mapTo(PaymentEntity entity) {
        Payment payment = new Payment();
        payment.setId(entity.id());
        payment.setOrderId(entity.orderId());
        payment.setUserId(entity.userId());
        payment.setAmount(entity.amount());
        payment.setStatus(entity.status());
        return payment;
    }

    public Map<String, Object> map(PaymentEntity payment) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", payment.id());
        map.put("orderId", payment.orderId());
        map.put("userId", payment.userId());
        map.put("amount", payment.amount());
        map.put("status", payment.status());
        return map;
    }

}
