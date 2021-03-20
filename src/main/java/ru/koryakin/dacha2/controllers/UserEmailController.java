package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.services.UserEmailService;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Slf4j
@Controller
public class UserEmailController {

    private final UserEmailService userEmailService;

    @Autowired
    public UserEmailController(UserEmailService userEmailService) {
        this.userEmailService = userEmailService;
    }

    @PostMapping(value = "/subscribe/")
    public String subscribe(@Valid @RequestParam(name = "email") String email) {
        userEmailService.save(new UserEmail(null, email, "подписан"));
        log.info("New Subscriber");
        return "redirect:" + "/index.html";
    }

    /*
            TODO: unsubscribe mechanism and email mailing
     */
}