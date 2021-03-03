package ru.koryakin.dacha2.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.services.VkService;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    VkService vkService;

    @GetMapping(value = "/")
    public String welcome(Model model) {
        model.addAttribute("tablebooking", new TableBooking());
        return "index";
    }

    @GetMapping(value = "/index.html")
    public String home(Model model){
        model.addAttribute("tablebooking", new TableBooking());
        return "index";
    }


    @RequestMapping(value = "/vk", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public String getVkWall() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String wallJSON = vkService.getWallPlainJSON();
        JsonNode rootNode = objectMapper.readTree(wallJSON);
        JsonNode first = rootNode.path("response").path("items").get(0);

        String prettyPrintWall = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(first);
        return prettyPrintWall;
    }

}
