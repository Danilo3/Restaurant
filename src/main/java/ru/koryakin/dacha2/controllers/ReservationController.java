package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.repositories.TableBookingRepository;
import ru.koryakin.dacha2.services.EmailService;

import javax.servlet.http.HttpServletRequest;


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

}