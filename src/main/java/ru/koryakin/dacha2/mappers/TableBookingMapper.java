package ru.koryakin.dacha2.mappers;

import org.mapstruct.*;
import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.TableBookingDto;
import ru.koryakin.dacha2.dto.UserEmailDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TableBookingMapper {

    TableBooking toTableBooking(TableBookingDto tableBookingDto);

    TableBookingDto toTableBookingDto(TableBooking tableBooking);

    List<TableBookingDto> toTableBookingDtos(List<TableBooking> tableBookingList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTableBookingFromDto(TableBookingDto dto, @MappingTarget TableBooking tableBooking);
}
