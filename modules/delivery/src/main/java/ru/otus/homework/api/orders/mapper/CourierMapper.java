package ru.otus.homework.api.orders.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.dto.Courier;
import ru.otus.homework.api.orders.entity.CourierEntity;

@Component
public class CourierMapper {

    public Courier map(CourierEntity entity) {
        Courier courier = new Courier();
        courier.setId(entity.id());
        courier.setName(entity.name());
        courier.setOrderId(entity.orderId());
        return courier;
    }

}
