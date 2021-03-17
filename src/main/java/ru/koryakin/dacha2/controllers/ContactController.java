package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.UserMessage;
import ru.koryakin.dacha2.services.EmailService;
import ru.koryakin.dacha2.services.UserMessageService;

@Slf4j
@Controller
public class ContactController {

    private final UserMessageService userMessageService;

    private EmailService emailService;

    @Autowired
    public ContactController(UserMessageService userMessageService) {
        this.userMessageService = userMessageService;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping(value = "/contact.html")
    public String contact(Model model) {
        model.addAttribute("usermessage", new UserMessage());
        return "contact";
    }

    @PostMapping(value = "/send")
    public String send(@ModelAttribute UserMessage message, Model model) {
        model.addAttribute("usermessage", message);
        userMessageService.save(message);
        log.info("Saved: " + message.toString());
        return "redirect:" + "contact.html";
    }

}