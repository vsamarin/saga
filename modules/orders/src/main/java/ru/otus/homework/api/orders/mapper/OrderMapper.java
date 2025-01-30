package ru.otus.homework.api.orders.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.dto.Order;
import ru.otus.homework.api.orders.entity.OrderEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class OrderMapper implements Mapper<OrderEntity, Order> {

    @Override
    public Order map(OrderEntity entity) {
        if (entity == null) return null;
        Order order = new Order();
        order.setId(entity.id());
        order.setUserId(entity.userId());
        order.setPrice(entity.price());
        order.setProduct(entity.product());
        order.setAddress(entity.address());
        order.setStatus(entity.status());
        return order;
    }

    public OrderEntity map(Order dto) {
        if (dto == null) return null;
        OrderEntity entity = new OrderEntity();
        entity.id(dto.getId());
        entity.userId(dto.getUserId());
        entity.price(dto.getPrice());
        entity.product(dto.getProduct());
        entity.address(dto.getAddress());
        entity.status(dto.getStatus());
        return entity;
    }

    public OrderEntity map(Map<String, Object> map) {
        if (map == null) return null;
        OrderEntity entity = new OrderEntity();
        entity.id(UUID.fromString((String) map.get("orderId")));
        entity.userId(UUID.fromString((String) map.get("userId")));
        entity.price(Long.valueOf(String.valueOf(map.get("price"))));
        entity.product(UUID.fromString((String) map.get("product")));
        entity.address(String.valueOf(map.get("address")));
        return entity;
    }

    public Map<String, Object> mapTo(OrderEntity entity) {
        if (entity == null) return null;
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", entity.id());
        map.put("userId", entity.userId());
        map.put("price", entity.price());
        map.put("product", entity.product());
        map.put("address", entity.address());
        map.put("status", entity.status());
        return map;
    }

}
