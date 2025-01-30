package ru.otus.homework.api.orders.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.dto.Product;
import ru.otus.homework.api.orders.entity.ProductEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ProductMapper implements Mapper<Map<String, Object>, ProductEntity> {

    @Override
    public ProductEntity map(Map<String, Object> map) {
        ProductEntity product = new ProductEntity();
        product.id(UUID.fromString(String.valueOf(map.get("product"))));
        product.orderId(UUID.fromString(String.valueOf(map.get("orderId"))));
        return product;
    }

    public Product mapTo(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.id());
        product.setOrderId(entity.orderId());
        product.setName(entity.name());
        product.setStatus(entity.status());
        return product;
    }

    public Map<String, Object> map(ProductEntity product) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", product.id());
        map.put("orderId", product.orderId());
        map.put("name", product.name());
        map.put("status", product.status());
        return map;
    }

}
