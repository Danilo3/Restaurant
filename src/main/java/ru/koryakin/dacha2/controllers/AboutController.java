package ru.koryakin.dacha2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping(value = {"/about.html", "/about"})
    public String about() {
        return "about";
    }

}
