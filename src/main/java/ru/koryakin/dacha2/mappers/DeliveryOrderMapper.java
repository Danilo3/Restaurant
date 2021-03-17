package ru.koryakin.dacha2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.koryakin.dacha2.domain.DeliveryOrder;
import ru.koryakin.dacha2.dto.DeliveryOrderDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DeliveryOrderMapper {

    DeliveryOrder toDeliveryOrder(DeliveryOrderDto deliveryOrderDto);
    DeliveryOrderDto toDeliveryOrderDto(DeliveryOrder deliveryOrder);
    List<DeliveryOrderDto> toDeliveryOrderDtos(List<DeliveryOrder> deliveryOrders);

}