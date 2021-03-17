package ru.koryakin.dacha2.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.dto.TableBookingDto;
import ru.koryakin.dacha2.services.TableBookingService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/booking")
public class TableBookingApiController {

    private final TableBookingService tableBookingService;

    @Autowired
    public TableBookingApiController(TableBookingService tableBookingService) {
        this.tableBookingService = tableBookingService;
    }

    @GetMapping(value = "/all/")
    public List<TableBookingDto> getAllBooking(){
        return tableBookingService.findAll();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteBookingById(@PathVariable(name = "id") Integer id){
        tableBookingService.deleteById(id);
        log.info("booking deleted");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateBooking(@PathVariable(name = "id") Integer id, @RequestBody TableBooking booking) {
        tableBookingService.save(booking);
        log.info(" booking updated");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TableBookingDto> createBooking(@RequestBody TableBooking booking) {
        tableBookingService.save(booking);
        log.info("booking created");
        return tableBookingService.findAll();
    }
}
