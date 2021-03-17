package ru.koryakin.dacha2.services;

import ru.koryakin.dacha2.domain.DeliveryOrder;
import ru.koryakin.dacha2.dto.DeliveryOrderDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {
    DeliveryOrderDto getDeliveryOrderByUsername(String username);

    void save(DeliveryOrder deliveryOrder);

    void save(DeliveryOrderDto deliveryOrderDto);

    List<DeliveryOrderDto> findAll();

    void deleteById(Integer id);

    boolean makeOrder(String cart, HttpServletResponse response, AtomicInteger userId);

    void finishOrder(DeliveryOrder deliveryOrder, HttpServletResponse response);

    DeliveryOrderDto getDeliveryOrder(HttpServletRequest request);
}
