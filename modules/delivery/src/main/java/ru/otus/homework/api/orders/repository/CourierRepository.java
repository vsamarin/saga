package ru.otus.homework.api.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.api.orders.entity.CourierEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, UUID> {
    Optional<CourierEntity> findByOrderId(UUID orderId);
}
