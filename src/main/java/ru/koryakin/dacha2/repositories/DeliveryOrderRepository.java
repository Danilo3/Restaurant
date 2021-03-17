package ru.koryakin.dacha2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryakin.dacha2.domain.DeliveryOrder;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Integer> {

    DeliveryOrder getDeliveryOrderByUsername(String username);

}
