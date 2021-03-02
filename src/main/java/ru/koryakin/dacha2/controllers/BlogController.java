package ru.koryakin.dacha2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @GetMapping(value = "/blog.html")
    public String blog(){
        return "blog";
    }

    @GetMapping(value = "/blog-detail.html")
    public String blogDetail(){
        return "blog-detail";
    }

}
