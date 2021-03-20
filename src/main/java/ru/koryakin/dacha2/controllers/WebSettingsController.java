package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class WebSettingsController {

    @Value("${robots.path}")
    private String robotsPath;

    @Value("${sitemap.path}")
    private String sitemapPath;

    @Value("${favicon.path}")
    private String faviconPath;

    @GetMapping(value = "/robots.txt")
    public void robots(HttpServletResponse response) {
        try {
            response.getWriter().write(FileUtils.readFileToString(new File(robotsPath), StandardCharsets.UTF_8));
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        } catch (IOException e) {
            log.warn("Exception while writing robots.txt: " + e.getMessage());
        }
    }

    @GetMapping(value = "/sitemap.xml")
    public void sitemap(HttpServletResponse response) {
        try {
            response.getWriter().write(FileUtils.readFileToString(new File(sitemapPath), StandardCharsets.UTF_8));
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_XML_VALUE);
        } catch (IOException e) {
            log.warn("Exception while writing sitemap.xml: " + e.getMessage());
        }
    }

    @GetMapping(value = {"/favicon.ico"})
    public void favicon(HttpServletResponse response) {
        try {
            byte[] arr = FileUtils.readFileToByteArray(new File(faviconPath));
            response.getOutputStream().write(arr);
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("image/x-icon");
        } catch (IOException e) {
            log.warn("Exception while writing favicon ico: " + e.getMessage());
        }
    }
}
