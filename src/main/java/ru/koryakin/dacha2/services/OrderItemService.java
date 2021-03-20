package ru.koryakin.dacha2.services;

import ru.koryakin.dacha2.domain.DeliveryOrderItem;
import ru.koryakin.dacha2.dto.DeliveryOrderItemDto;

import java.util.ArrayList;
import java.util.List;

public interface OrderItemService {

    void saveAll(List<DeliveryOrderItem> items);

    void flush();

    void saveAllItemDtos(List<DeliveryOrderItemDto> orderItems);

    List<DeliveryOrderItemDto> findAll();
}
