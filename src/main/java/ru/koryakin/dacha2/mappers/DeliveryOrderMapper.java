package ru.koryakin.dacha2.mappers;

import org.mapstruct.*;
import ru.koryakin.dacha2.domain.DeliveryOrder;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.DeliveryOrderDto;
import ru.koryakin.dacha2.dto.UserEmailDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DeliveryOrderMapper {

    DeliveryOrder toDeliveryOrder(DeliveryOrderDto deliveryOrderDto);

    DeliveryOrderDto toDeliveryOrderDto(DeliveryOrder deliveryOrder);

    List<DeliveryOrderDto> toDeliveryOrderDtos(List<DeliveryOrder> deliveryOrders);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDeliveryOrderFromDto(DeliveryOrderDto dto, @MappingTarget DeliveryOrder deliveryOrder);
}