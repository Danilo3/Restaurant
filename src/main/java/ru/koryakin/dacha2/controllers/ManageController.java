package ru.koryakin.dacha2.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.model.UserMessage;
import ru.koryakin.dacha2.repositories.UserMessageRepository;

import java.util.Optional;


@Controller
public class ManageController {

    private static final Logger logger = LoggerFactory.getLogger(ManageController.class);

    @Autowired
    private UserMessageRepository userMessageRepository;

    @GetMapping(value = "/manage.html")
    public String manage(){
        return "manage";
    }

    @GetMapping(value = "/manage")
    @ResponseBody
    public String manageAPI() {
        return HttpStatus.OK.toString();
    }

    @GetMapping(value = "/manage/getMessages/all")
    public @ResponseBody Iterable<UserMessage> getAllMessages() {
        return userMessageRepository.findAll();
    }

    @RequestMapping(value = "/manage/messages/")
    public String manageMessages(Model model){
        model.addAttribute("msgList", userMessageRepository.findAll());
        return "messages.html";
    }

    @DeleteMapping("/manage/messages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserMessage(@PathVariable Integer id){
        Optional<UserMessage> message = userMessageRepository.findById(id);
        userMessageRepository.deleteById(id);
        message.ifPresent(userMessage -> logger.info("[DELETE] Message: " + userMessage));
    }
}
