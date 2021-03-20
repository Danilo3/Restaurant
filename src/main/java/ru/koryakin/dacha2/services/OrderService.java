package ru.koryakin.dacha2.services;

import ru.koryakin.dacha2.domain.DeliveryOrder;
import ru.koryakin.dacha2.dto.DeliveryOrderDto;
import ru.koryakin.dacha2.dto.DeliveryOrderItemDto;

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

    DeliveryOrderDto findById(Integer id);

    void update(Integer id, DeliveryOrderDto deliveryOrderDto);

    List<DeliveryOrderItemDto> getDeliveryOrderItemsById(Integer id);

    List<DeliveryOrderItemDto> saveOrderItems(Integer id, List<DeliveryOrderItemDto> orderItems);

    void updateOrderItems(Integer id, List<DeliveryOrderItemDto> orderItems);

    DeliveryOrderItemDto getItemByOrderIdAndItemId(Integer order_id, Integer item_id);
}
