package ru.koryakin.dacha2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String welcome(Model model) {
        return "index";
    }

    @GetMapping(value = "/index.html")
    public String home(Model model){
        return "index";
    }

}
