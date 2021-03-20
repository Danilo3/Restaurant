package ru.koryakin.dacha2.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.domain.DeliveryOrderItem;
import ru.koryakin.dacha2.dto.DeliveryOrderItemDto;
import ru.koryakin.dacha2.mappers.OrderItemMapper;
import ru.koryakin.dacha2.repositories.DeliveryOrderItemRepository;
import ru.koryakin.dacha2.services.OrderItemService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final DeliveryOrderItemRepository deliveryOrderItemRepository;

    private final OrderItemMapper mapper;

    @Autowired
    public OrderItemServiceImpl(DeliveryOrderItemRepository deliveryOrderItemRepository, OrderItemMapper mapper) {
        this.deliveryOrderItemRepository = deliveryOrderItemRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveAll(List<DeliveryOrderItem> items) {
        deliveryOrderItemRepository.saveAll(items);
    }

    @Override
    public void flush() {
        deliveryOrderItemRepository.flush();
    }

    @Override
    public void saveAllItemDtos(List<DeliveryOrderItemDto> orderItems) {
        saveAll(mapper.toDeliveryOrderItems(orderItems));
    }

    @Override
    public List<DeliveryOrderItemDto> findAll() {
        return mapper.toDeliveryOrderItemDtos(deliveryOrderItemRepository.findAll());
    }
}

