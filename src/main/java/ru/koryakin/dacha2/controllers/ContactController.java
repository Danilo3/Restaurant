package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.UserMessage;
import ru.koryakin.dacha2.repositories.UserMessageRepository;
import ru.koryakin.dacha2.services.EmailService;

@Controller
@Slf4j
public class ContactController {

//    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private UserMessageRepository userMessageRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/contact.html")
    public String contact(Model model){
        model.addAttribute("usermessage", new UserMessage());
        return "contact";
    }

    @PostMapping(value = "/send")
    public String send(@ModelAttribute UserMessage message, Model model) {
        model.addAttribute("usermessage", message);
        userMessageRepository.save(message);
        log.info("Saved: " + message.toString());
        emailService.sendSimpleMessage("contact@dacha-restaurant.ru", "New message from site", message.toText());
        log.info("Send: " + message.toString());
        return "redirect:" + "contact.html";
    }

}
