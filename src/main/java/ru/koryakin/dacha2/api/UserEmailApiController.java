package ru.koryakin.dacha2.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.UserEmailDto;
import ru.koryakin.dacha2.services.UserEmailService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/email")
public class UserEmailApiController {

    private final UserEmailService userEmailService;

    @Autowired
    public UserEmailApiController(UserEmailService userEmailService) {
        this.userEmailService = userEmailService;
    }

    @GetMapping(value = "/all/")
    public List<UserEmailDto> getAllEmails() {
        return userEmailService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEmailDto getOneById(@PathVariable("id") Integer id) {
        return userEmailService.findById(id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUserEmailById(@PathVariable(name = "id") Integer id) {
        userEmailService.deleteById(id);
        log.info("users email was deleted");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUserEmail(@PathVariable(name = "id") Integer id, @RequestBody UserEmailDto userEmailDto) {
        userEmailService.update(id, userEmailDto);
        log.info("user email was updated");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEmailDto> createUserEmail(@RequestBody UserEmailDto userEmailDto) {
        userEmailService.save(userEmailDto);
        log.info("user email was created");
        return userEmailService.findAll();
    }
}
