package ru.koryakin.dacha2.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.UserMessage;
import ru.koryakin.dacha2.dto.UserMessageDto;
import ru.koryakin.dacha2.services.UserMessageService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/messages")
public class UserMessageApiController {

    private final UserMessageService userMessageService;

    @Autowired
    public UserMessageApiController(UserMessageService userMessageService) {
        this.userMessageService = userMessageService;
    }

    @GetMapping(value = "/all/")
    public List<UserMessageDto> getAllMessages(){
        return userMessageService.findAll();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteMessageById(@PathVariable(name = "id") Integer id){
        userMessageService.deleteById(id);
        log.info("message was deleted");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUserMessage(@PathVariable(name = "id") Integer id, @RequestBody UserMessage userMessage) {
        userMessageService.save(userMessage);
        log.info("user message was updated");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMessageDto> createUserMessage(@RequestBody UserMessage userMessage) {
        userMessageService.save(userMessage);
        log.info("new user message was created");
        return userMessageService.findAll();
    }
}
