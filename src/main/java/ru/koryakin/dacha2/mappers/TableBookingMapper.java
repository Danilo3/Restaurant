package ru.koryakin.dacha2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.dto.TableBookingDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TableBookingMapper {

   TableBooking toTableBooking(TableBookingDto tableBookingDto);
   TableBookingDto toTableBookingDto(TableBooking tableBooking);
   List<TableBookingDto> toTableBookingDtos(List<TableBooking> tableBookingList);

}
