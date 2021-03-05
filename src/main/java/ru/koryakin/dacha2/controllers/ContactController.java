package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ru.koryakin.dacha2.domain.SystemMessage;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.domain.UserMessage;
import ru.koryakin.dacha2.repositories.UserEmailRepository;
import ru.koryakin.dacha2.repositories.UserMessageRepository;
import ru.koryakin.dacha2.services.EmailService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
@Validated
public class ContactController {

//    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private UserMessageRepository userMessageRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/contact.html")
    public String contact(Model model) {
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

    @Autowired
    private UserEmailRepository userEmailRepository;

    private Set<String> errors = new HashSet<>();

//    @PostMapping(value = "/subscribe")
//    public String subscribe(@ModelAttribute(name = "useremail") @Valid UserEmail userEmail, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("sysmessage", new SystemMessage("Failed", "Email введен не верно!"));
//            errors.clear();
//        } else {
//            model.addAttribute("sysmessage", new SystemMessage("Success", "Вы подписаны!"));
//        }
//        userEmail.setSubscribed(true);
//        userEmailRepository.save(userEmail);
//        log.info("New Subscriber: " + userEmail.toString());
//        return "redirect:" + "index.html";
//    }


    @PostMapping(value = "/subscribe/")
    public String subscribe(@Email @RequestParam(name = "email") String email, Model model) {
        if (!errors.isEmpty()) {
            model.addAttribute("sysmessage", new SystemMessage("Failed", "Email введен не верно!"));
            errors.clear();
        } else {
            model.addAttribute("sysmessage", new SystemMessage("Success", "Вы подписаны!"));
        }
        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(email);
        userEmail.setSubscribed(true);
        userEmailRepository.save(userEmail);
        log.info("New Subscriber: " + userEmail.toString());
        return "redirect:" + "/index.html";
    }


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(ConstraintViolationException ex) {
        errors = ex.getConstraintViolations().stream().map(Objects::toString).collect(Collectors.toSet());
        String body = "Invalid email";
        try {
            body = (FileUtils.readFileToString(
                    new File("src/main/resources/templates/400.html"), StandardCharsets.UTF_8));
        } catch (IOException exception) {
            log.info("something strange ith file");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}