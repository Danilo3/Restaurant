package ru.koryakin.dacha2.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class LoginController {

    @GetMapping(value = {"/login", "/login.html"})
    public String login(){
        log.info("Someone try to login");
        return "login";
    }
}
