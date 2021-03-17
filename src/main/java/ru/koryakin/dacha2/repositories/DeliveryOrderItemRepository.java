package ru.koryakin.dacha2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryakin.dacha2.domain.DeliveryOrderItem;

public interface DeliveryOrderItemRepository extends JpaRepository<DeliveryOrderItem, Integer> {
}
