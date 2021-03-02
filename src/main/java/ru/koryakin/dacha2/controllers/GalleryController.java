package ru.koryakin.dacha2.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {

    @GetMapping(value = "/gallery.html")
    public String gallery(){
        return "gallery";
    }
}
