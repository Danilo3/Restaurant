package ru.koryakin.dacha2.mappers;

import org.mapstruct.*;
import ru.koryakin.dacha2.domain.DeliveryOrderItem;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.DeliveryOrderItemDto;
import ru.koryakin.dacha2.dto.UserEmailDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OrderItemMapper {

    DeliveryOrderItem toDeliveryOrderItem(DeliveryOrderItemDto deliveryOrderItemDto);

    DeliveryOrderItemDto toDeliveryOrderItemDto(DeliveryOrderItem deliveryOrderItem);

    List<DeliveryOrderItemDto> toDeliveryOrderItemDtos(List<DeliveryOrderItem> deliveryOrderItem);

    List<DeliveryOrderItem> toDeliveryOrderItems(List<DeliveryOrderItemDto> deliveryOrderItemDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDeliveryOrderItemFromDto(DeliveryOrderItemDto dto, @MappingTarget DeliveryOrderItem deliveryOrderItem);
}
