package ru.koryakin.dacha2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.koryakin.dacha2.domain.DeliveryOrderItem;
import ru.koryakin.dacha2.dto.DeliveryOrderItemDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OrderItemMapper {

    DeliveryOrderItem toDeliveryOrderItem(DeliveryOrderItemDto deliveryOrderItemDto);
    DeliveryOrderItemDto toDeliveryOrderItemDto(DeliveryOrderItem deliveryOrderItem);
    List<DeliveryOrderItemDto> toDeliveryOrderItemDtos(List<DeliveryOrderItem> deliveryOrderItem);

}
