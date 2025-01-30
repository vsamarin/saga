package ru.otus.homework.api.orders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.api.orders.entity.ProductEntity;
import ru.otus.homework.api.orders.repository.ProductRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity reserve(ProductEntity entity) {
        return productRepository
                .findById(entity.id())
                .map(p -> productRepository.save(p.status(ProductEntity.Status.RESERVED)))
                .orElseThrow(() -> new RuntimeException(String.format("Product %s not found failed", entity.id())));
    }

    public void available(UUID orderId) {
        productRepository
                .findByOrderId(orderId)
                .map(p -> productRepository.save(p.orderId(null).status(ProductEntity.Status.AVAILABLE)));
    }

}
