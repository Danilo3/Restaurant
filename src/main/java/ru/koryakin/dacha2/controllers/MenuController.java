package ru.koryakin.dacha2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.koryakin.dacha2.model.UserMessage;

@Controller
public class MenuController {

    @GetMapping(value = "/menu.html")
    public String menu() {
        return "menu";
    }
}
