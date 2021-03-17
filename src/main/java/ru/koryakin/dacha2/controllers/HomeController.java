package ru.koryakin.dacha2.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.services.impl.BlogPostServiceImpl;
import ru.koryakin.dacha2.services.VkService;

@Controller
@Slf4j
public class HomeController {

    private final VkService vkService;

    private final BlogPostServiceImpl blogPostService;

    @Autowired
    public HomeController(VkService vkService, BlogPostServiceImpl blogPostService) {
        this.vkService = vkService;
        this.blogPostService = blogPostService;
    }

    @GetMapping(value = {"/", "/index.html"})
    public String welcome(Model model) {
        addAttributes(model);
        return "index";
    }

    @GetMapping(value = "/vk", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public String getVkWall() {
       return vkService.getWallPlainJSON();
    }

    private void addAttributes(Model model){
        model.addAttribute("tablebooking", new TableBooking());
        model.addAttribute("latestPosts", blogPostService.getNLatestPostsFromRepository(3));
        model.addAttribute("useremail", new UserEmail());
        model.addAttribute("intro", blogPostService.findAllIntro().subList(0, 3));
        model.addAttribute("events", blogPostService.findAllEvents().subList(0, 3));
    }
}