package ru.koryakin.dacha2.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.annotations.EmailSend;
import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.dto.TableBookingDto;
import ru.koryakin.dacha2.mappers.TableBookingMapper;
import ru.koryakin.dacha2.repositories.TableBookingRepository;
import ru.koryakin.dacha2.services.TableBookingService;

import java.util.List;

@Slf4j
@Service
public class TableBookingServiceImpl implements TableBookingService {

    private final TableBookingMapper mapper;

    private final TableBookingRepository tableBookingRepository;

    @Autowired
    public TableBookingServiceImpl(TableBookingMapper mapper, TableBookingRepository tableBookingRepository) {
        this.mapper = mapper;
        this.tableBookingRepository = tableBookingRepository;
    }

    @Override
    public List<TableBookingDto> findAll() {
        return mapper.toTableBookingDtos(tableBookingRepository.findAll());
    }

    @Override
    @EmailSend(subject = "new reservation")
    public void save(TableBooking booking) {
        tableBookingRepository.save(booking);
    }

    @Override
    public void deleteById(Integer id) {
        tableBookingRepository.deleteById(id);
    }

    @Override
    public void save(TableBookingDto bookingDto) {
        this.save(mapper.toTableBooking(bookingDto));
    }

    @Override
    public TableBookingDto findById(Integer id) {
        return mapper.toTableBookingDto(tableBookingRepository.getById(id));
    }

    @Override
    public void update(Integer id, TableBookingDto bookingDto) {
        TableBooking booking = tableBookingRepository.findById(id).orElseThrow();
        mapper.updateTableBookingFromDto(bookingDto, booking);
        tableBookingRepository.save(booking);
    }
}
