package ru.koryakin.dacha2.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.model.UserMessage;
import ru.koryakin.dacha2.repositories.UserMessageRepository;

@Controller
public class ContactController {

    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private UserMessageRepository userMessageRepository;

    @GetMapping(value = "/contact.html")
    public String contact(Model model){
        model.addAttribute("usermessage", new UserMessage());
        return "contact";
    }

    @PostMapping(value = "/send")
    public String send(@ModelAttribute UserMessage message, Model model) {
        model.addAttribute("usermessage", message);
        userMessageRepository.save(message);
        logger.info("Saved: " + message.toString());
        return "redirect:" + "contact.html";
    }

}
