package ru.koryakin.dacha2.services;

import ru.koryakin.dacha2.domain.DeliveryOrderItem;

import java.util.ArrayList;

public interface OrderItemService {

    void saveAll(ArrayList<DeliveryOrderItem> items);

    void flush();
}
