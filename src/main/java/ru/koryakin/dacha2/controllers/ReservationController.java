package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.services.EmailService;
import ru.koryakin.dacha2.services.TableBookingService;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
public class ReservationController {

    private final TableBookingService tableBookingService;

    @Autowired
    public ReservationController(TableBookingService tableBookingService) {
        this.tableBookingService = tableBookingService;
    }

    @GetMapping(value = "/reservation.html")
    public String reservation(Model model) {
        model.addAttribute("tablebooking", new TableBooking());
        return "reservation";
    }

    @PostMapping(value = "/reserve")
    public String reserve(@ModelAttribute TableBooking booking, Model model, HttpServletRequest request) {
        model.addAttribute("tablebooking", booking);
        log.info("Booking made: " + booking.toString());
        tableBookingService.save(booking);
        log.info("Booking saved: " + booking.toString());
        String referer = request.getHeader("Referer");
        if (referer.isEmpty()) {
            referer = "/";
        }
        return "redirect:" + referer;
    }
}