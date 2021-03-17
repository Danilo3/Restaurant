package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class DachaHttpErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorRequestUri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI).toString();
        int statusCode = 0;
        if (status != null) {
            statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                log.warn("Not found: " + errorRequestUri);
                return "404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "login";
            } else if (statusCode == HttpStatus.BAD_GATEWAY.value()) {
                return "404";
            }
        }
        log.warn("Happened strange: " + HttpStatus.valueOf(statusCode).toString() + " " + errorRequestUri);
        return "error";
    }
}