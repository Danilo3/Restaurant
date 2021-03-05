package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.repositories.TableBookingRepository;
import ru.koryakin.dacha2.services.EmailService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
public class ReservationController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TableBookingRepository tableBookingRepository;

    @GetMapping(value = "/reservation.html")
    public String reservation(Model model){
        model.addAttribute("tablebooking", new TableBooking());
        return "reservation";
    }

    @PostMapping(value = "/reserve")
    public String reserve(@ModelAttribute TableBooking booking, Model model, HttpServletRequest request) {
        model.addAttribute("tablebooking", booking);
        log.info("Booking made: " + booking.toString());
        tableBookingRepository.save(booking);
        log.info("Booking saved: " + booking.toString());
        emailService.sendSimpleMessage("contact@dacha-restaurant.ru", "New reservation from site", booking.toText());
        log.info("Booking send: " + booking.toString());
        String referer = request.getHeader("Referer");
        if (referer.isEmpty()) {
            referer = "/";
        }
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/api/booking/all/", method = RequestMethod.GET)
    public @ResponseBody List<TableBooking> getAllBooking(){
        ArrayList<TableBooking> bookings = new ArrayList<>();
        for (TableBooking booking: tableBookingRepository.findAll()) {
            bookings.add(booking);
        }
        return bookings;
    }

    @RequestMapping(value = "/api/booking/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deleteBookingById(@PathVariable(name = "id") Integer id){
        tableBookingRepository.deleteById(id);
        log.info("booking deleted");
        return "{\"httpStatus\": \"ok\"}";

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccessControlAllowOrigin("*");
//        headers.setAccessControlAllowCredentials(true);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }

    @RequestMapping(value = "/api/booking/{id}", method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateBookingById(@PathVariable(name = "id") Integer id,
                                                  @RequestBody TableBooking booking) {
        tableBookingRepository.save(booking);
        log.info(" booking updated");
        return "{\"httpStatus\": \"ok\"}";
    }

    @RequestMapping(value = "/api/booking/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<TableBooking> createBooking(@RequestBody TableBooking booking) {
        tableBookingRepository.save(booking);
        log.info("booking created");
        ArrayList<TableBooking> bookings = new ArrayList<>();
        for (TableBooking tableBooking: tableBookingRepository.findAll()) {
            bookings.add(tableBooking);
        }
        return bookings;
    }
}