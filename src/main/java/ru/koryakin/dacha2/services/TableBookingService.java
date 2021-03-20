package ru.koryakin.dacha2.services;

import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.dto.TableBookingDto;

import java.util.List;

public interface TableBookingService {

    List<TableBookingDto> findAll();

    void save(TableBooking booking);

    void deleteById(Integer id);

    void save(TableBookingDto bookingDto);

    TableBookingDto findById(Integer id);

    void update(Integer id, TableBookingDto bookingDto);

}
