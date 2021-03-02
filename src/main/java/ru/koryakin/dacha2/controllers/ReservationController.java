package ru.koryakin.dacha2.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.koryakin.dacha2.model.TableBooking;


@Controller
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @GetMapping(value = "/reservation.html")
    public String reservation(Model model){
        model.addAttribute("tablebooking", new TableBooking());
        return "reservation";
    }

    @PostMapping(value = "/reserve")
    public String reserve(@ModelAttribute TableBooking booking, Model model) {
        model.addAttribute("tablebooking", booking);
        logger.info(booking.toString());
        return "redirect:" + "reservation.html";
    }
}